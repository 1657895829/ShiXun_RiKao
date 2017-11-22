package com.bwie.secondweek_moni.view_jiekou;

//注册时在view层回调判断数据的接口
public interface RegViewCallBack {
    //注册判断非空
    public void regTelEmpty();
    public void regPwdEmpty();
    public void regSuccess();
    public void regFailed();
}
