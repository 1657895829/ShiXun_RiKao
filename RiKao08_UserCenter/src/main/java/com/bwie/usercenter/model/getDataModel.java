package com.bwie.usercenter.model;
import com.bwie.usercenter.bean.LoginBean;
import com.bwie.usercenter.bean.PersonBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * MVP的三层编写步骤：
 * 1.1   再写model层接口实现的普通类，进行数据的存取
 */
public class getDataModel {
    //通过登录获取数据
    public void LoginData(String tel, String pwd, final ModelCallBack.LoginInterface callBack){
         /*
        登录接口：
        http://120.27.23.105/user/login
        请求参数：
        mobile 	手机号	 必填
        password 		密码 		必填*/
        //使用OKHttp请求网络数据，在当前项目Model添加依赖：compile 'com.squareup.okhttp3:okhttp:3.9.0'
        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()  //使用post请求时，可用表单内容来进行数据请求
                .add("mobile",tel)
                .add("password",pwd)
                .build();
        Request request = new Request.Builder()
                .url("http://120.27.23.105/user/login")
                .post(body)
                .build();
        //使用实现的接口call对象把请求内容添加进队列中进行获取
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败时，回传一个值 1
                callBack.failed(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //成功时，获取响应的内容，callBack 接口回调返回到presenter层
                String result = response.body().string();
                LoginBean loginBean = new Gson().fromJson(result, LoginBean.class);
                    int uid = loginBean.getData().getUid();
                if(uid<0){
                    callBack.success(1799);
                }else{
                    callBack.success(uid);
                }
            }
        });
    }

    //通过注册获取数据
    public void RegData(String tel, String pwd, final ModelCallBack.RegInterface callBack){
         /*
        登录接口：
        http://120.27.23.105/user/login
        请求参数：
        mobile 	手机号	 必填
        password 		密码 		必填*/
        //使用OKHttp请求网络数据，在当前项目Model添加依赖：compile 'com.squareup.okhttp3:okhttp:3.9.0'
        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()  //使用post请求时，可用表单内容来进行数据请求
                .add("mobile",tel)
                .add("password",pwd)
                .build();
        Request request = new Request.Builder()
                .url("http://120.27.23.105/user/reg")
                .post(body)
                .build();
        //使用实现的接口call对象把请求内容添加进队列中进行获取
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败时，回传一个值 1
                callBack.failed(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //成功时，获取响应的内容，callBack 接口回调返回到presenter层
                String result = response.body().string();
                callBack.success(result);
            }
        });
    }

    //个人中心 获取数据
    public void PersonData(String uid, final ModelCallBack.PersonInterface callBack){
         /*
        登录接口：
        http://120.27.23.105/user/login
        请求参数：
        mobile 	手机号	 必填
        password 		密码 		必填*/
        //使用OKHttp请求网络数据，在当前项目Model添加依赖：compile 'com.squareup.okhttp3:okhttp:3.9.0'
        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()  //使用post请求时，可用表单内容来进行数据请求
                .add("uid",uid)
                .build();
        Request request = new Request.Builder()
                .url("http://120.27.23.105/user/getUserInfo")
                .post(body)
                .build();
        //使用实现的接口call对象把请求内容添加进队列中进行获取
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败时，回传一个值 1
                callBack.failed(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //成功时，获取响应的内容，callBack 接口回调返回到presenter层
                String result = response.body().string();
                PersonBean personBean = new Gson().fromJson(result, PersonBean.class);
                callBack.success(personBean);
            }
        });
    }
}
