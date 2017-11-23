package com.dhw.lastmonthexam.model;

import com.dhw.lastmonthexam.bean.SongBean;
import com.dhw.lastmonthexam.okhttp.AbstractUiCallBack;
import com.dhw.lastmonthexam.okhttp.OkhttpUtils;

//Model层
public class MyModel {
    public void onRefresh(boolean up, final ModelCallBack callBack) {

        OkhttpUtils.getInstance01().asy(null, "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0", new AbstractUiCallBack<SongBean>() {
            @Override
            public void success(SongBean songBean) {
                callBack.success(songBean);
            }

            @Override
            public void failure(Exception e) {
                callBack.failure(e);
            }
        });
    }

    public interface ModelCallBack{
        public void success (SongBean songBean);
        public void failure (Exception e);
    }
}
