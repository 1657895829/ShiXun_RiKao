package com.bwie.weekone_moni_jiekou;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.bwie.weekone_moni_jiekou.view.CustomCircleView;
import com.bwie.weekone_moni_jiekou.view.TitleView;

public class MainActivity extends AppCompatActivity {
    private CustomCircleView custom_circle;
    private TitleView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //拿到圆形滚动条和自定义标题栏的视图
        custom_circle = (CustomCircleView) findViewById(R.id.custom_Circle);
        titleView = (TitleView) findViewById(R.id.titleView);

        //调用自定义控件类中接口，实现按钮的点击事件
        titleView.setOnTitleViewClickListener(new TitleView.OnTitleViewClickListener() {
            @Override
            public void titleViewButtonClicked(View view) {
                //判断当前点击的view是哪个按钮
                if (view.getId() == R.id.button_backward){       //左边的返回按钮
                    Toast.makeText(MainActivity.this, "点击返回", Toast.LENGTH_SHORT).show();
                    finish();
                }else if  (view.getId() == R.id.button_forward){ //右边的登录按钮{
                    //点击右边的按钮 跳转到 SecondActivity,显示矩形view
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    //点击按钮,调用滚动条的方法,扫描二维码
    public void saoMiaoCode(View view) {
        custom_circle.start();
    }
}
