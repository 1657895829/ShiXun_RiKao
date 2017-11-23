package com.dhw.lastmonthexam.model;

import com.dhw.lastmonthexam.bean.ShopBean;
import com.dhw.lastmonthexam.okhttp.AbstractUiCallBack;
import com.dhw.lastmonthexam.okhttp.OkhttpUtils;

public class MainModel {

    public void getData(final MainModelCallBack callBack){


        OkhttpUtils.getInstance02().asy(null, "http://120.27.23.105/product/getCarts?uid=100", new AbstractUiCallBack<ShopBean>() {
            @Override
            public void success(ShopBean bean) {

                callBack.success(bean);
            }

            @Override
            public void failure(Exception e) {

                callBack.failure(e);
            }
        });

    }


}
