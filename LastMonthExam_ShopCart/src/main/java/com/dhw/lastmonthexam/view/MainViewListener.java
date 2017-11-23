package com.dhw.lastmonthexam.view;

import com.dhw.lastmonthexam.bean.ShopBean;

public interface MainViewListener {
    public void success(ShopBean bean);
    public void failure(Exception e);

}
