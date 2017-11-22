package com.bwie.secondweek.view;
import com.bwie.secondweek.bean.MyDataBean;
/**
 * view层，UI界面的搭建
 */
public interface DataView {
    public void success(MyDataBean dataBean);   //成功获取数据
    public void failure(Exception e);            //数据获取失败
}
