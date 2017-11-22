package com.bwie.usercenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.usercenter.MainActivity;
import com.bwie.usercenter.R;
import com.bwie.usercenter.presenter.getDataPresenter;
import com.bwie.usercenter.view.InterfaceView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
//注册页面
public class RegActivity extends AppCompatActivity implements InterfaceView.SecondInterface{
    @Bind(R.id.back)
    TextView back;
    @Bind(R.id.regTel)
    EditText regTel;
    @Bind(R.id.regPwd)
    EditText regPwd;
    @Bind(R.id.regNow)
    Button regNow;
    private getDataPresenter dataPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        dataPresenter = new getDataPresenter(this);
    }

    //登录验证方法
    private void login(View view) {
        //调用presenter中的注册方法
        dataPresenter.reg(regTel.getText().toString(),regPwd.getText().toString());
    }

    @OnClick({R.id.back, R.id.regNow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                //点击返回按钮，跳转到登录页面
                Intent intent = new Intent(RegActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.regNow:
                login(view);
                break;
        }
    }

    @Override
    public void failed(int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void sucess(Object object) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void telEmpty() {
        Toast.makeText(RegActivity.this,"手机号为空 或 格式不正确！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwdEmpty() {
        Toast.makeText(RegActivity.this,"密码为空 或 长度小于6位！",Toast.LENGTH_SHORT).show();
    }
}
