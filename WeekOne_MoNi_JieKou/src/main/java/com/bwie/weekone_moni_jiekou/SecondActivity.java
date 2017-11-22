package com.bwie.weekone_moni_jiekou;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//显示矩形view的页面
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //点击第二个页面的返回按钮跳转至上一个页面
        Button button_backward = (Button) findViewById(R.id.button_backward);
        button_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
