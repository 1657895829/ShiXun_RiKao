package com.bwie.usercenter.view;
/**
 * MVP的三层编写步骤：
 * 2. View 层 数据展示类
 * View – 用户界面：View通常是指Activity、Fragment或者某个View控件，它含有一个Presenter成员变量。
 通常View需要实现一个逻辑接口，将View上的操作转交给Presenter进行实现，最后，Presenter 调用View逻辑接口将结果返回给View元素。
 */
public interface InterfaceView {
    //登录页面由presenter层交互数据到view层MainActivity
    public interface FirstInterface{
        //手机号为空时调用的方法
        public void telEmpty();
        //密码为空时调用的方法
        public void pwdEmpty();

        //登录时，数据获取成功的方法，返回一个值表示登陆成功
        void success(Object object);
        //登录时，数据获取失败的方法，返回一个int值响应码表示登陆失败
        void failed(int code);
    }

    //注册页面由presenter层到view层RegActivity
    public interface SecondInterface{
        void failed(int i);
        void sucess(Object object);

        //手机号为空时调用的方法
        public void telEmpty();
        //密码为空时调用的方法
        public void pwdEmpty();
    }
}
