package com.dhw.three.callback;


import com.dhw.three.bean.DingdanBean;

/**
 * Created by Menglucywhh on 2017/11/20.
 */
public interface MyViewCallBack {
    public void viewSuccess(DingdanBean dingdanBean);
    public void viewFail(Exception e);
}
