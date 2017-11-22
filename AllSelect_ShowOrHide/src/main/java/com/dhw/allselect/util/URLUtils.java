package com.dhw.allselect.util;
import com.dhw.allselect.bean.Beans;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

public interface URLUtils {
    @GET("v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0")
    Observable<Beans> getObservable();

    OkHttpClient okHttpClient=new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();
    //使用Retrofit进行设置
    Retrofit retrofit=new Retrofit.Builder()
            //添加OkHttpClient
            .client(okHttpClient)
            //添加接口头部
            .baseUrl("http://tingapi.ting.baidu.com/")
            //添加支持RxJAva
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            //设置解析方式
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    //获得描述网络接口的实例
    URLUtils utils = retrofit.create(URLUtils.class);
}
