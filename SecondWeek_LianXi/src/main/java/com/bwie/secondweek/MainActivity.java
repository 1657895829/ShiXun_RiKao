package com.bwie.secondweek;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.bwie.secondweek.adapter.DataAdapter;
import com.bwie.secondweek.bean.MyDataBean;
import com.bwie.secondweek.presenter.DataPresenter;
import com.bwie.secondweek.view.DataView;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import butterknife.Bind;
import butterknife.ButterKnife;
//主功能代码类，实现view层，进行数据UI的获取
public class MainActivity extends AppCompatActivity implements DataView {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.springview)
    SpringView springview;
    private DataPresenter dataPresenter;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //声明presenter层
        dataPresenter = new DataPresenter(this);

        //设置布局适配器,,,线性布局管理器
        adapter = new DataAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //设置SpringView进行多条目加载的头布局和尾布局
        springview.setHeader(new DefaultHeader(this));
        springview.setFooter(new DefaultFooter(this));

        //设置SpringView的刷新监听事件
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                dataPresenter.onRefresh(true);
            }

            @Override
            public void onLoadmore() {
                dataPresenter.onRefresh(false);
            }
        });

        dataPresenter.onRefresh(true);  //设置一直刷新数据
    }

    @Override
    public void success(MyDataBean dataBean) {
        //只要有数据就一直加载
        if (springview != null){
            springview.onFinishFreshAndLoad();
        }
        adapter.addData(dataBean.getResult().getData());
    }

    @Override
    public void failure(Exception e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"数据出错",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
