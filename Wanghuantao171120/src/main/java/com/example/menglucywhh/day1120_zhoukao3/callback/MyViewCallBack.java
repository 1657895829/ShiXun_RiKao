package com.example.menglucywhh.day1120_zhoukao3.callback;

import com.example.menglucywhh.day1120_zhoukao3.bean.DingdanBean;

/**
 * Created by Menglucywhh on 2017/11/20.
 */
public interface MyViewCallBack {
    public void viewSuccess(DingdanBean dingdanBean);
    public void viewFail(Exception e);
}
