package com.bwie.usercenter.model;

import com.bwie.usercenter.bean.PersonBean;

/**
 * MVP的三层编写步骤：
 * 1. 先写model层接口类，进行数据的存取
 */
public interface ModelCallBack {
    public interface LoginInterface{
        //登录时，数据获取成功的方法，返回一个值表示登陆成功
        public void success(Object object);
        //登录时，数据获取失败的方法，返回一个int值响应码表示登陆失败
        public void failed(int code);
    }

    public interface RegInterface {
        //注册时，数据获取成功的方法，返回一个值表示登陆成功
        public void success(Object object);
        //注册时，数据获取失败的方法，返回一个int值响应码表示登陆失败
        public void failed(int code);
    }

    //个人中心
    public interface PersonInterface{
        void success(PersonBean personBean);
        void failed(int code);
    }
}
