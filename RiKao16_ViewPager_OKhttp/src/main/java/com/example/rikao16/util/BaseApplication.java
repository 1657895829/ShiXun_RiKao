package com.example.rikao16.util;

import android.app.Application;
//全局初始化Application类
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //配置imageLoader
        ImageLoaderUtil.init(this);
    }
}
