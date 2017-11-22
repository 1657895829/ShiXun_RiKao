package com.dhw.allselect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dhw.allselect.adapter.MyAdapter;
import com.dhw.allselect.bean.Beans;
import com.dhw.allselect.presenter.Presenters;
import com.dhw.allselect.view.Iview;
import com.facebook.drawee.backends.pipeline.Fresco;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Iview {

    private Presenters presenters;
    private TextView mBianji;
    private RecyclerView mRv;
    private CheckBox mSelectall;
    private LinearLayout mLl;
    List<Beans.SongListBean> listBeen;
    boolean isShow=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        presenters = new Presenters(this, this);
        initView();

        presenters.getData();
    }
    private void initView() {
        mBianji = (TextView) findViewById(R.id.bianji);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mSelectall = (CheckBox) findViewById(R.id.selectall);
        mLl = (LinearLayout) findViewById(R.id.ll);
        LinearLayoutManager ll=new LinearLayoutManager(this);
        mRv.setLayoutManager(ll);
        //全选
        mSelectall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mRv.setAdapter(new MyAdapter(MainActivity.this,listBeen,true,true));
                }else {
                    mRv.setAdapter(new MyAdapter(MainActivity.this,listBeen,true,false));
                }
            }
        });
        mBianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShow){
                    mBianji.setText("完成");
                    mLl.setVisibility(View.VISIBLE);
                    mRv.setAdapter(new MyAdapter(MainActivity.this,listBeen,isShow,false));
                    isShow=false;
                }else {
                    mBianji.setText("编辑");
                    mBianji.setVisibility(View.GONE);
                    mRv.setAdapter(new MyAdapter(MainActivity.this,listBeen,isShow,false));
                    isShow=true;
                }
            }
        });
    }
    @Override
    public void setData(List<Beans.SongListBean> list) {
        listBeen=list;
        Log.i("===+",""+list.get(0).getTitle());
        MyAdapter adapter=new  MyAdapter(MainActivity.this,listBeen,false,false);
        mRv.setAdapter(adapter);

    }
}
