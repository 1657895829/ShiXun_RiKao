package com.bwie.secondweek_moni.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.secondweek_moni.R;
import com.bwie.secondweek_moni.activity.SecondActivity;
import com.bwie.secondweek_moni.activity.ThirdActivity;
import com.bwie.secondweek_moni.presenter.MyPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//登录页
public class Login_Fragment extends Fragment {
    @Bind(R.id.tel_Login)
    EditText telLogin;
    @Bind(R.id.pwd_Login)
    EditText pwdLogin;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.qqLogin)
    Button qqLogin;
    private MyPresenter myPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myPresenter = new MyPresenter(getActivity(), new MyPresenter.LoginViewCallBack() {
            @Override
            public void loginTelEmpty() {
                Toast.makeText(getActivity(),"登录时 手机号不能为空",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void loginPwdEmpty() {
                Toast.makeText(getActivity(),"登录时 密码不能为空",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void loginSuccess() {
                Toast.makeText(getActivity(),"登录成功!即将跳转到主页面",Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getActivity(), ThirdActivity.class);
                startActivity(intent);
            }

            @Override
            public void loginFailed() {
                Toast.makeText(getActivity(),"登录失败!用户信息输入错误...",Toast.LENGTH_SHORT).show();
            }
        }, new MyPresenter.RegViewCallBack() {
            @Override
            public void regTelEmpty() {
            }

            @Override
            public void regPwdEmpty() {
            }

            @Override
            public void regSuccess() {
            }

            @Override
            public void regFailed() {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.login,R.id.qqLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:    //登录按钮的非空判断
                myPresenter.login_PanDuan(telLogin.getText().toString(),pwdLogin.getText().toString());
                break;
            case R.id.qqLogin:    //第三方QQ登录
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                startActivity(intent);
                break;
        }
    }
}
