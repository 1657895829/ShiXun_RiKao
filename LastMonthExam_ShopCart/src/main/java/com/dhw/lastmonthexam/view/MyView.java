package com.dhw.lastmonthexam.view;

import com.dhw.lastmonthexam.bean.SongBean;

//View层
public interface MyView {
    public void success (SongBean songBean);
    public void failure (Exception e);
}
