package com.bwie.secondweek.okhttp;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

//拦截器类
public class LoggingInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();  //请求对象
        /**
         * 返回最准确的可用系统计时器的当前值，以毫微秒为单位
         * 返回值表示从某一固定但任意的时间算起的毫微秒数
         */
        long time1 = System.nanoTime();

        Response response = chain.proceed(request);  //响应对象
        long time2 = System.nanoTime();

        System.out.println("响应时长 = "+(time2 - time1));
        return response;
    }
}
