package com.bwie.duhongwang;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.bwie.duhongwang.adapter.MyAdapter;
import com.bwie.duhongwang.bean.ProductsBean;
import com.bwie.duhongwang.presenter.DataPresenter;
import com.bwie.duhongwang.view.DataView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//列表页
public class MainActivity extends AppCompatActivity implements DataView {
    @Bind(R.id.grid_icon)
    ImageView gridIcon;
    @Bind(R.id.find)
    Button find;
    @Bind(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;
    @Bind(R.id.editWords)
    EditText editWords;
    private int num = 1;    //判断变量改变布局
    private int page = 1;   //初始化页数数据
    private MyAdapter adapter;
    private Handler handler = new Handler();
    private DataPresenter dataPresenter = new DataPresenter(this,this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //默认显示一页数据
        dataPresenter.getData("笔记本","1");
    }

    //view 层 的方法 用来更新ui
    @Override
    public void showView(ProductsBean productsBean) {

        //设置线性布局管理器,加载数据
        if (num % 2 == 1) {
            //设置线性布局管理器,加载数据
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
            xRecyclerView.setLayoutManager(layoutManager);
            gridIcon.setBackgroundResource(R.drawable.lv_icon);
        }
        //设置布局适配器,,,线性布局管理器,加载数据
        adapter = new MyAdapter(MainActivity.this,  productsBean);
        xRecyclerView.setAdapter(adapter);

        //XRecyclerview的上拉下拉方法
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //在子线程内上拉刷新数据
                        dataPresenter.getData(editWords.getText().toString(),"1");
                        adapter.notifyDataSetChanged();
                        xRecyclerView.refreshComplete();
                    }
                },800);
            }

            @Override
            public void onLoadMore() {
                //在子线程内下拉加载数据
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        dataPresenter.getData(editWords.getText().toString(),page+"");
                        adapter.notifyDataSetChanged();
                        xRecyclerView.loadMoreComplete();
                    }
                },800);
            }
        });
    }

    @OnClick({R.id.grid_icon, R.id.find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.grid_icon:
                //根据num变量是奇数还是偶数来判断加载哪种布局，并返回对应布局的图片
                num++;
                if (num % 2 == 0) {
                    //设置网格布局管理器,加载数据
                    GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
                    xRecyclerView.setLayoutManager(layoutManager);
                    gridIcon.setBackgroundResource(R.drawable.grid_icon);
                }
                if (num % 2 == 1) {
                    //设置线性布局管理器,加载数据
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                    xRecyclerView.setLayoutManager(layoutManager);
                    gridIcon.setBackgroundResource(R.drawable.lv_icon);
                }
                break;
            case R.id.find:
                //点击搜索按钮时触发presenter的获取数据方法
                dataPresenter.getData(editWords.getText().toString(),"1");
                break;
            default:
                break;
        }
    }

    //实现presenter内部的防止内存溢出方法
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataPresenter.destory();
    }
}
