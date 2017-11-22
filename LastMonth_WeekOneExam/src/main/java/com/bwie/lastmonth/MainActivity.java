package com.bwie.lastmonth;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.bwie.lastmonth.view.CustomCircleView;

public class MainActivity extends AppCompatActivity {
    private CustomCircleView roundProgressBar_01 = null;
    private int TIME_TICKER = 0;
    private int progress = 0;
    private final int UPDATE_UI_01 = 1;
    private boolean flag;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);

            switch(msg.what){
                case UPDATE_UI_01:
                    if(progress > 100){
                        progress = 0;
                    }
                    progress++;
                    handler.sendEmptyMessageDelayed(UPDATE_UI_01, 300);
                    updateUIRoundProgressBar_01(progress);
                    break;
            }
        }
    };

    private Button start,chongzhi,change;
    private CustomCircleView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到控件
        start = (Button) findViewById(R.id.start);
        chongzhi = (Button) findViewById(R.id.chongzhi);
        change = (Button) findViewById(R.id.change);
        myView = (CustomCircleView) findViewById(R.id.roundProgressBar_01);
        init();
        //开始的点击事件
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessageDelayed(UPDATE_UI_01, 300);
            }
        });
        //重置的点击事件
        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = 0;
            }
        });
        //改变颜色的点击事件
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    myView.setMyColor(Color.RED);
                    flag = false;
                }else {
                    myView.setMyColor(Color.BLUE);
                    flag = true;
                }
            }
        });
    }

    private void init() {
        // TODO Auto-generated method stub
        roundProgressBar_01 = (CustomCircleView) findViewById(R.id.roundProgressBar_01);
        roundProgressBar_01.setStyle(TIME_TICKER);
        roundProgressBar_01.setProgress(1);
        progress = 1;
    }

    private void updateUIRoundProgressBar_01(int progress){
        roundProgressBar_01.setProgress(progress);
    }
}