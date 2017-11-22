package com.bwie.secondweek_moni.presenter;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.bwie.secondweek_moni.model.MyModel;
import com.bwie.secondweek_moni.view_jiekou.LoginModelCallBack;
import com.bwie.secondweek_moni.view_jiekou.RegModelCallBack;

//Presenter层 调用model中的注册 和 登录的方法
public class MyPresenter {
    FragmentActivity activity;
    //获取登录和注册时view层数据,声明为当前变量
    LoginViewCallBack loginViewCallBack;
    RegViewCallBack regViewCallBack;
    public MyPresenter(FragmentActivity activity, LoginViewCallBack loginViewCallBack, RegViewCallBack regViewCallBack) {
        this.activity = activity;
        this.loginViewCallBack = loginViewCallBack;
        this.regViewCallBack = regViewCallBack;
    }

    //获取model层
    MyModel model = new MyModel();

    //注册时在view层回调判断数据的接口
    public interface RegViewCallBack {
        //注册判断非空
        public void regTelEmpty();
        public void regPwdEmpty();
        public void regSuccess();
        public void regFailed();
    }

    //登录时在view层回调判断数据的接口
    public interface LoginViewCallBack {
        //登陆判断非空
        public void loginTelEmpty();
        public void loginPwdEmpty();
        public void loginSuccess();
        public void loginFailed();
    }

    //在Presenter层逻辑判断注册时非空
    public void reg_PanDuan(String tel,String pwd){
        if (TextUtils.isEmpty(tel)){
            //回调判断注册手机号时的接口方法
            regViewCallBack.regTelEmpty();
            return;
        }else if (TextUtils.isEmpty(pwd)){
            //回调判断注册密码时的接口方法
            regViewCallBack.regPwdEmpty();
            return;
        }
        //如果注册成功就调用注册接口的成功方法
        model.reg(activity, tel, pwd, new RegModelCallBack() {
            @Override
            public void regSuccess() {
                regViewCallBack.regSuccess();
            }
        });
    }

    //在Presenter层逻辑判断登录时非空
    public void login_PanDuan(String tel,String pwd){
        if (TextUtils.isEmpty(tel)){
            //回调判断登录手机号时的接口方法
            loginViewCallBack.loginTelEmpty();
            return;
        }else if (TextUtils.isEmpty(pwd)){
            //回调判断登录密码时的接口方法
            loginViewCallBack.loginPwdEmpty();
            return;
        }
        //调用登录接口的方法
        model.login(activity, tel, pwd, new LoginModelCallBack() {
            @Override
            public void loginSuccess() {
                loginViewCallBack.loginSuccess();
            }

            @Override
            public void loginFailed() {
                loginViewCallBack.loginFailed();
            }
        });
    }
}
