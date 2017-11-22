package com.bwie.BallViewDemo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import com.bwie.BallViewDemo.customView.BallView;

/*  引用自定义控件，第二种：代码中引用   */
public class BallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取容器
        RelativeLayout container  = (RelativeLayout) findViewById(R.id.relativeLayout);

        //引用自定义控件
        BallView ballView = new BallView(this);

        //添加到容器
        container.addView(ballView);
    }
}
