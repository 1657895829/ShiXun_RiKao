package com.dhw.lastmonthexam.model;
import com.dhw.lastmonthexam.bean.ShopBean;

public interface MainModelCallBack {


    public void success(ShopBean bean);
    public void failure(Exception e);

}
