package com.bwie.duhongwang.model;
import com.bwie.duhongwang.okhttp.OkHttp3Utils;
import okhttp3.Callback;
/**
 * model层，查询数据的接口实现类
 */
public class MyDataModel {
    //传三个参数（公共请求参数） 一个是分页加载时用到的 page 还有  输入框内搜索的值，还有callback 用来在presenter内拿出bean
    public void getData(String keywords, String page, Callback callback ){
        OkHttp3Utils.doGet("http://120.27.23.105/product/searchProducts?keywords="+keywords+"&page="+page+"&source=android",callback);
    }
}
