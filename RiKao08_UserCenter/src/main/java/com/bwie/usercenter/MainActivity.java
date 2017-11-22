package com.bwie.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.usercenter.activity.PersonInfoActivity;
import com.bwie.usercenter.activity.RegActivity;
import com.bwie.usercenter.presenter.getDataPresenter;
import com.bwie.usercenter.shared.SharedUtils;
import com.bwie.usercenter.view.InterfaceView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * MVP的三层编写步骤：
 * 2.1 View 层 数据实现view层的接口类，重写其方法
 */
public class MainActivity extends AppCompatActivity implements InterfaceView.FirstInterface{
    @Bind(R.id.tel)
    EditText tel;
    @Bind(R.id.pwd)
    EditText pwd;
    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.reg)
    Button reg;
    private getDataPresenter dataPresenter;
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    Intent intent = new Intent(MainActivity.this,PersonInfoActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dataPresenter = new getDataPresenter(this);

        //调用工具类判断保存的布尔值
        boolean b = SharedUtils.getBooleanData(MainActivity.this, "flag", false);

        if (b) {	//已经进入过，现在是第二次，进入个人中心页面
            handler.sendEmptyMessageDelayed(0, 0);
        }else{		//现在是第一次，进入登录页面
            SharedUtils.savaBooleanData(MainActivity.this, "flag", true);
        }
    }

    @OnClick({R.id.btn, R.id.reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                login(view);
                break;
            case R.id.reg:
                Intent intent = new Intent(MainActivity.this, RegActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    //重写方法
    @Override
    public void telEmpty() {
        Toast.makeText(MainActivity.this,"手机号为空 或 格式不正确！",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void pwdEmpty() {
        Toast.makeText(MainActivity.this,"密码为空 或 长度小于6位！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(Object object) {
        //登录成功后，保存用户id到sp，跳转到个人中心页面
        Intent intent = new Intent(MainActivity.this, PersonInfoActivity.class);
        intent.putExtra("uid",(int)object);
        startActivity(intent);
        finish();
    }

    @Override
    public void failed(int code) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"没有用户,请先注册！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //登录验证方法
    private void login(View view) {
        dataPresenter.login(tel.getText().toString(),pwd.getText().toString());
    }
}
