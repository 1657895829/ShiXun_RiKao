package com.bwie.thirdlogin_qq;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

//显示用户数据的页面
public class SecondActivity extends AppCompatActivity{
    private ImageView headPhoto;
    private TextView userName;
    private TextView userSex;
    private Button outLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();     //初始化数据

        //设置用户信息
        Intent intent=getIntent();
        String photo = intent.getStringExtra("headPhoto");
        String name=   intent.getStringExtra("name");
        String sex=   intent.getStringExtra("gender");
        Glide.with(this).load(photo).into(headPhoto);
        userName.setText("               "+name);
        userSex.setText("               "+sex);
    }

    private void initView() {
        headPhoto = (ImageView) findViewById(R.id.headPhoto);
        userName = (TextView) findViewById(R.id.name);
        userSex = (TextView) findViewById(R.id.sex);
        outLogin = (Button) findViewById(R.id.outLogin);
        outLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
