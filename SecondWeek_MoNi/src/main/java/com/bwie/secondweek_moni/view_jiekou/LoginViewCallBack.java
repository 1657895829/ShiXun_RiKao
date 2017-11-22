package com.bwie.secondweek_moni.view_jiekou;

//登录时在view层回调判断数据的接口
public interface LoginViewCallBack {
    //登陆判断非空
    public void loginTelEmpty();
    public void loginPwdEmpty();
    public void loginSuccess();
    public void loginFailed();
}
