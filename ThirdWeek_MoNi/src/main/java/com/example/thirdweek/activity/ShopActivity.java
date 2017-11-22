package com.example.thirdweek.activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.thirdweek.R;
import com.example.thirdweek.adapter.CartAdapter;
import com.example.thirdweek.bean.ShopBean;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShopActivity extends AppCompatActivity{
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.selectAll)
    CheckBox selectAll;
    @Bind(R.id.sumCount)
    TextView sumCount;
    @Bind(R.id.sumPrice)
    TextView sumPrice;
    private List<ShopBean.DataBean.ListBean> list = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);

        getData();                //获取网络数据

        selectAll.setTag(1);     //默认复选按钮都为 未选中 状态

        //设置布局管理器,适配器
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new CartAdapter(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //适配器单个条目复选按钮的点击事件,价格变化
        adapter.setCheckBoxListener(new CartAdapter.CheckBoxListener() {
            @Override
            public void check(int position, int count, boolean check, List<ShopBean.DataBean.ListBean> list) {
                sumPrice(list);
            }
        });

        //适配器单个条目加减号按钮的点击事件,价格变化
        adapter.setCustomViewListener(new CartAdapter.CustomViewListener() {
            @Override
            public void click(int count, List<ShopBean.DataBean.ListBean> list) {
                sumPrice(list);
            }
        });

        //适配器单个条目删除按钮的点击事件,总价变化
        adapter.setDelListener(new CartAdapter.DelListener() {
            @Override
            public void del(int position, List<ShopBean.DataBean.ListBean> list) {
                sumPrice(list);
            }
        });
    }

    public  void getData(){
        //使用OKhttp请求网络数据
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://120.27.23.105/product/getCarts?uid=100")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<ShopBean.DataBean> data = new Gson().fromJson(response.body().string(), ShopBean.class).getData();
                for (int i = 0; i < data.size(); i++) {
                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        list.add(data.get(i).getList().get(j));
                    }
                }

                //更新ui,适配器添加数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //本地数据可直接添加数据
                        //网络请求使用(异步),直接添加数据为空,异步获取数据源后再添加数据
                        adapter.add(list);
                    }
                });
            }
        });
    }

    //计算商品总价,设置没选择商品时总价为0，商品数量为0
    float price = 0;
    int count;
    public void sumPrice(List<ShopBean.DataBean.ListBean> list) {
        price = 0;
        count = 0;
        boolean allCheck = true;
        //遍历数据源
        for (ShopBean.DataBean.ListBean bean : list) {
            if (bean.isCheck()) {        //商品选中时，计算总价
                price += bean.getPrice() * bean.getNum();
                count += bean.getNum();
            } else {                    //只要有一个商品未选中，全选按钮 应该设置成 未选中
                allCheck = false;
            }
        }

        //设置商品总数及总价
        sumPrice.setText("总价：￥"+price);
        sumCount.setText("商品总数："+count+"  件");

        //全选按钮是否选中时的tag值
        if (allCheck){
            selectAll.setTag(2);
            selectAll.setChecked(true);
        }else {
            selectAll.setTag(1);
            selectAll.setChecked(false);
        }
    }

    /**
     * 全选按钮 点击事件
     * 点击全选按钮设置所有复选框状态为true，否则为false
     * 1：未选中，2：选中
     */
    boolean select = false;
    @OnClick(R.id.selectAll)
    public void onViewClicked() {
        int tag = (Integer) selectAll.getTag();
        if (tag == 1) {
            selectAll.setTag(2);
            select = true;
        } else {
            selectAll.setTag(1);
            select = false;
        }
        //遍历数据源,根据tag值设置选择状态，然后刷新改变后的数据，重新添加
        for (ShopBean.DataBean.ListBean bean : list) {
            bean.setCheck(select);
        }
        adapter.notifyDataSetChanged();
        sumPrice(adapter.getList());
    }
}
