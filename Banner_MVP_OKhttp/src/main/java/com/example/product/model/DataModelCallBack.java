package com.example.product.model;
import okhttp3.Callback;

//model层接口
public interface DataModelCallBack {
    public void showData(int pid, Callback callback);
}
