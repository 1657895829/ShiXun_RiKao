package com.dhw.three;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.dhw.three.adapter.MyRecyAdapter;
import com.dhw.three.bean.DingdanBean;
import com.dhw.three.callback.MyViewCallBack;
import com.dhw.three.callback.MyViewCallBack2;
import com.dhw.three.presenter.MyPresenter;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import java.util.ArrayList;
import java.util.List;

//主页面功能代码
public class MainActivity extends AppCompatActivity implements MyViewCallBack {
    private RecyclerView recyclerView;
    private List<DingdanBean.DataBean> listDingdan;
    private MyRecyAdapter myRecyAdapter;
    private ImageView imageView;
    private SpringView springView;
    int page=2;
    int status = 0;
    private MyPresenter myPresenter;
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        springView = (SpringView) findViewById(R.id.springView);
        imageView = (ImageView) findViewById(R.id.image);
        group = (RadioGroup) findViewById(R.id.group);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //new出P层对象
        myPresenter = new MyPresenter(this);

        //调用p层的方法
        myPresenter.getDataFromModel(page);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        //springview的设置,实现下拉刷新展示第一页数据、上拉加载更多实现分页功能
        springView.setHeader(new DefaultHeader(MainActivity.this));
        springView.setFooter(new DefaultFooter(MainActivity.this));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                myPresenter.getDataFromModel(page);
                springView.onFinishFreshAndLoad();

                Toast.makeText(MainActivity.this,"下拉刷新成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadmore() {
                page++;
                myPresenter.getDataFromModel(page);
                springView.onFinishFreshAndLoad();
                Toast.makeText(MainActivity.this,"上拉加载成功",Toast.LENGTH_SHORT).show();
            }
        });

        //引入popuowindow 的布局文件
       View contentView = View.inflate(MainActivity.this,R.layout.popup_window,null);
       //父窗体
        final View parent = View.inflate(MainActivity.this,R.layout.activity_main,null);
        //通过构造方法创建一个popupwindow
        final PopupWindow popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        //出现的问题,需要设置4个下面的
        popupWindow.setTouchable(true);//窗体可以触摸
        popupWindow.setFocusable(true);//让窗体获取焦点
        popupWindow.setOutsideTouchable(true);//设置窗体外部可以触摸
        popupWindow.setBackgroundDrawable(new BitmapDrawable());//设置背景

        //获取自定义的popupwindow里面的id,3个textview
        TextView daizhifu = (TextView) contentView.findViewById(R.id.daizhifu);
        TextView yizhifu = (TextView) contentView.findViewById(R.id.yizhifu);
        TextView yiquxiao = (TextView) contentView.findViewById(R.id.yiquxiao);

        daizhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();//弹窗取消
                status=0;
                listDingdan.clear();
              myPresenter.popUpQingqiu(status);
                Toast.makeText(MainActivity.this,status+"",Toast.LENGTH_SHORT).show();
            }
        });

        yizhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                status=1;
                listDingdan.clear();
                myPresenter.popUpQingqiu(status);
                Toast.makeText(MainActivity.this,status+"",Toast.LENGTH_SHORT).show();
            }
        });

        yiquxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                status=2;
                listDingdan.clear();
                myPresenter.popUpQingqiu(status);
                Toast.makeText(MainActivity.this,status+"",Toast.LENGTH_SHORT).show();
            }
        });

        //点击右上角的图片 出来popupwindow,实现右上角订单状态筛选功能和订单列表页签切换的筛选功能；
       imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                   /**
                    * （3）showAtLocation(View contentView, int gravity, int x, int y)方法
                    *          View contentView：指定的布局View
                    *          int gravity ：调整PopupWindow窗口在指定的布局View中的位置（如表示：Gravity.RIGHT | Gravity.TOP 右上角 中间用 | 连接）
                    *          int x ：设置 x 轴偏移量（可用负数）正数越大表示：向左走；越小向右走；
                    *          int y ：设置 y 轴偏移量（可用负数）正数越大表示：向下走；越小向上走；
                    */
                   //显示popupwindow
                    popupWindow.showAtLocation(group, Gravity.RIGHT | Gravity.TOP, 0 ,290 );//显示在控件的左下方

           }
       });
    }


    //实现接口重写的方法
    @Override
    public void viewSuccess(DingdanBean dingdanBean) {
          if(listDingdan==null){
              listDingdan=new ArrayList<>();
          }

        listDingdan.addAll(dingdanBean.getData());
        //new适配器,实现RecyclerView局部刷新机制，实时刷新订单列表部分数据
        if(myRecyAdapter==null) {
            myRecyAdapter = new MyRecyAdapter(MainActivity.this,listDingdan, new MyViewCallBack2() {
                @Override
                public void viewSuccess2(final String data) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,data,Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void viewFail2() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"网络慢",Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });
           // myRecyAdapter.addData(listDingdan);
            recyclerView.setAdapter(myRecyAdapter);
        }else{
            myRecyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void viewFail(Exception e) {
        System.out.println("异常 : "+e);
    }


    //避免MVP内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();

        myPresenter.detach();
    }
}
