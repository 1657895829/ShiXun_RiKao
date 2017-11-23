package com.dhw.lastmonthexam;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.dhw.lastmonthexam.adapter.MyAdapter;
import com.dhw.lastmonthexam.bean.SongBean;
import com.dhw.lastmonthexam.presenter.MyPresenter;
import com.dhw.lastmonthexam.view.MyView;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import butterknife.Bind;
import butterknife.ButterKnife;

//RecyclerView条目信息页面
public class MainActivity extends AppCompatActivity implements MyView{
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.springView)
    SpringView springView;
    private MyAdapter adapter;
    private MyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //关联p层
        presenter = new MyPresenter(this);

        //设置线性布局管理器，布局适配器
        adapter = new MyAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //设置SpringView的滑动监听事件
        springView.setHeader(new DefaultHeader(this));
        springView.setFooter(new DefaultFooter(this));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh(true);
            }

            @Override
            public void onLoadmore() {
                presenter.onRefresh(false);
            }
        });
        presenter.onRefresh(true);
    }

    @Override
    public void success(SongBean songBean) {
        if (springView != null){
            springView.onFinishFreshAndLoad();
        }
      adapter.add(songBean.getSong_list());
    }

    @Override
    public void failure(Exception e) {

                Toast.makeText(MainActivity.this, " error ", Toast.LENGTH_SHORT).show();

    }
}
