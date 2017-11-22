package com.example.rikao16;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rikao16.adapter.ImageAdapter;
import com.example.rikao16.bean.Bean;
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

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.yuanJia)
    TextView yuanJia;
    @Bind(R.id.youHui)
    TextView youHui;
    @Bind(R.id.addCart)
    Button addCart;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.lineLayout)
    LinearLayout lineLayout;
    private List<ImageView> images;
    private List<Bean.DataBean> list;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                int currentItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(currentItem + 1);

                //延时发送消息
                handler.sendEmptyMessageDelayed(0, 3000);
            }
        }
    };
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getData();
    }

    //获取网络数据的方法
    private void getData() {
        OkHttpClient client = new OkHttpClient();
        //请求参数体
        Request request = new Request.Builder()
                .url("https://www.zhaoapi.cn/product/getProductDetail?pid=1")
                .build();
        //call对象
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //成功的返回 string只能使用一次
                // System.out.println("lalal:"+response.body().string());
                String json = response.body().string();
                Bean bean = new Gson().fromJson(json, Bean.class);
                if(list == null){
                    list = new ArrayList<>();
                }
                list.add(bean.getData());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //设置商品信息显示
                        title.setText(list.get(0).getTitle());
                        yuanJia.setText("原价:￥"+ list.get(0).getPrice());

                        /**
                         getPaint().setAntiAlias(true);// 抗锯齿
                         getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
                         getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中间横线（删除线）
                       */
                        yuanJia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                        youHui.setText("优惠价:￥"+ list.get(0).getBargainPrice());

                        initCircle();   //显示小圆点

                        //设置无限轮播图的初始位置，轮播图每3秒切换一张
                        viewPager.setCurrentItem(100000);
                        viewPager.setCurrentItem(list.size()%100000);
                        handler.sendEmptyMessageDelayed(0,3000);

                        setAdapter();  //设置适配器

                        //viewPager页面滑动时小圆点的位置
                        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                //设置圆点的颜色,实现小圆点跟随轮播图一起切换
                                for (int i = 0 ;i<images.size();i++){
                                    if (i == position%images.size()){
                                        images.get(i).setImageResource(R.drawable.shape_select);
                                    }else {
                                        images.get(i).setImageResource(R.drawable.shape_select_no);
                                    }
                                }
                            }

                            @Override
                            public void onPageSelected(int position) {
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {
                            }
                        });
                    }
                });
            }
        });
    }


    //点击加入购物车的方法
    @OnClick(R.id.addCart)
    public void onViewClicked() {
            //路径
            String path = "https://www.zhaoapi.cn/product/addCart?uid=71&pid=1";
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(path)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String body = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //吐司加入购物车成功
                            Toast.makeText(MainActivity.this,body,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }

    //设置适配器的方法
    public void setAdapter(){
        if(imageAdapter==null) {
            imageAdapter = new ImageAdapter(MainActivity.this, list);
            viewPager.setAdapter(imageAdapter);
        }else{
            imageAdapter.notifyDataSetChanged();
        }
    }

    //初始化小圆点的方法
    private void initCircle() {
        //首先需要一个集合记录这些小圆点的图片,,,,当页面切换的时候,可以从集合中取出imageView进行显示图片的设置
        images = new ArrayList<>();
        //再清除线性布局和集合中的view视图
        lineLayout.removeAllViews();
        images.clear();

        //遍历集合数据对应的圆点（图片数据为集合数据：list.size()）
        for (int i = 0;i< 3;i++){
            //先初始化一个ImageView视图
            ImageView imageView = new ImageView(MainActivity.this);

            //如果当前是第一页,就设置选中的图片
            if (i == 0){
                imageView.setImageResource(R.drawable.shape_select);
            }else {
                imageView.setImageResource(R.drawable.shape_select_no);
            }

            //然后把设置好的视图添加到集合中
            images.add(imageView);

            //最后把视图添加到线性布局和集合中显示
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5,0,5,0);
            lineLayout.addView(imageView,params);
        }
    }
}
