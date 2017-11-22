package com.bwie.duhongwang.okhttp;
import android.os.Handler;
import java.util.concurrent.TimeUnit;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpUtils {
    private Handler handler=new Handler();
    public Handler getHandler(){
        return handler;
    }
    //单例
    private static OkHttpUtils okHttpUtils = null;
    private OkHttpUtils(){};
    public static OkHttpUtils getInstance(){
        if(okHttpUtils == null){            //不适用拦截器时，可以把if判断内的代码去除
            okHttpUtils = new OkHttpUtils();
            client = new OkHttpClient.Builder()
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20,TimeUnit.SECONDS)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(new LoggingInterceptor())
                    .build();
        }
        return okHttpUtils;
    }

    private static OkHttpClient client;
    private void initOkHttpClient(){
        if (client==null){
            client=new OkHttpClient.Builder().build();
        }
    }
    //公用的get请求方法  完成的功能不确定
    public void doGet(String url, Callback callback){
        initOkHttpClient();
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }
}
