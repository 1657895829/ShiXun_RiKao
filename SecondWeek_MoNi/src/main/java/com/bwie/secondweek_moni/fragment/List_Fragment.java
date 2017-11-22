package com.bwie.secondweek_moni.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.bwie.secondweek_moni.R;
import com.bwie.secondweek_moni.adapter.DataAdapter;
import com.bwie.secondweek_moni.bean.DataBean;
import com.bwie.secondweek_moni.okhttp.AbstractUiCallBack;
import com.bwie.secondweek_moni.okhttp.OkhttpUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import butterknife.Bind;
import butterknife.ButterKnife;

//列表页
public class List_Fragment extends Fragment {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.springView)
    SpringView springView;
    int page = 0;     //初始化为第0页数据

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //设置线性布局管理器,加载数据
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        getData();

        //设置SpringView进行多条目加载的头布局和尾布局
        springView.setHeader(new DefaultHeader(getActivity()));
        springView.setFooter(new DefaultFooter(getActivity()));

        //设置SpringView的刷新监听事件
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {       //上拉刷新显示数据更多
                page++;
                getData();
                springView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {      //下拉加载初始化页数据
                page = 0;
                getData();
                springView.onFinishFreshAndLoad();
            }
        });
    }

    public void getData(){
        String path = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset="+page;
        OkhttpUtils.getInstance().asy(null, path, new AbstractUiCallBack<DataBean>() {
            @Override
            public void success(DataBean dataBean) {
                //获取数据 .调用适配器中的添加数据的方法，，刷新添加到前面
                DataAdapter adapter = new DataAdapter(getActivity());
                adapter.addData(dataBean.getSong_list());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void failure(Exception e) {
                Toast.makeText(getActivity(),"数据错误"+e,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
