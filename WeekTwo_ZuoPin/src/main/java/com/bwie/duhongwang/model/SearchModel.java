package com.bwie.duhongwang.model;
//model层，数据的查询接口
public interface SearchModel {
        public void getData(String keywords, String page, okhttp3.Callback callback);
}
