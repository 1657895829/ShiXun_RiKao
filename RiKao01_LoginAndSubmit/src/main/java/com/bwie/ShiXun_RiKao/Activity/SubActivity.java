package com.bwie.ShiXun_RiKao.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.bwie.ShiXun_RiKao.MainActivity;
import com.bwie.ShiXun_RiKao.R;

public class SubActivity extends AppCompatActivity {
    private EditText tel;
    private EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        tel = (EditText) findViewById(R.id.tel);
        pwd = (EditText) findViewById(R.id.pwd);
    }

    public void submit(View view) {
        if (TextUtils.isEmpty(tel.getText().toString()) && TextUtils.isEmpty(pwd.getText().toString())){
            Toast.makeText(SubActivity.this,"注册失败！手机号或密码不能为空！",Toast.LENGTH_SHORT).show();
        }else {
            //注册成功后，toast提示“注册成功”,并跳转到登录页面
            Toast.makeText(SubActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SubActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
