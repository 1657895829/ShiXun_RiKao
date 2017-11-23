package com.dhw.lastmonthexam.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by muhanxi on 17/11/10.
 *
 *  * Okhttp 单例 范型的封装
 */

public  abstract  class AbstractUiCallBack<T> implements Callback {

    /**
     * 成功回调
     * @param t
     */
    public abstract void success(T t);

    /**
     * 失败回调
     * @param e
     */
    public abstract void failure(Exception e);


    private Handler handler = null ;
    private Class clazz ;

    public AbstractUiCallBack(){
        handler = new Handler(Looper.getMainLooper());


        //  得到的是一个 AbstractUiCallBack<T> 的Type
       Type type =  getClass().getGenericSuperclass() ;

        // 得到的是T的实际Type
       Type [] arr =  ((ParameterizedType)type).getActualTypeArguments() ;

        clazz = (Class) arr[0] ;



    }




    @Override
    public void onFailure(Call call, final IOException e) {


        handler.post(new Runnable() {
            @Override
            public void run() {
                failure(e);
            }
        });

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        try {

            if(response.code() == 200){
                String result = response.body().string();
                System.out.println("result = " + result);
                Gson gson = new Gson();

                final T t = (T) gson.fromJson(result,clazz);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        success(t);
                    }
                });
            }else{
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        failure(new Exception());
                    }
                });
            }

        } catch (final IOException e) {
            e.printStackTrace();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    failure(e);
                }
            });
        } catch (final JsonSyntaxException e) {
            e.printStackTrace();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    failure(e);
                }
            });        }


    }
}
