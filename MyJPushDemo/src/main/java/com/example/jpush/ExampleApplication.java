package com.example.jpush;
import android.app.Application;
import cn.jpush.android.api.JPushInterface;
/**
 * 极光推送自动集成步骤，具体操作步骤参照以下链接文档（结合上课录屏）：
 * https://docs.jiguang.cn/jpush/client/Android/android_guide/
 *
 * 极光推送第三方初始化 Application
 */
public class ExampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
