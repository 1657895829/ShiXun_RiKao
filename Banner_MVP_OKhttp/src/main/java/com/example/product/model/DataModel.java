package com.example.product.model;
import com.example.product.okhttp.OkHttpUtils;
import okhttp3.Callback;

//model层接口的实现类
public class DataModel implements DataModelCallBack {
    @Override
    public void showData(int pid, Callback callback) {
        OkHttpUtils.getInstance().doGet("https://www.zhaoapi.cn/product/getProductDetail?pid="+pid+"",callback);
    }
}
