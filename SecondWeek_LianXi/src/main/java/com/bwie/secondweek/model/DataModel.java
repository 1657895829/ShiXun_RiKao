package com.bwie.secondweek.model;
import com.bwie.secondweek.bean.MyDataBean;
import com.bwie.secondweek.okhttp.AbstractUiCallBack;
import com.bwie.secondweek.okhttp.OkhttpUtils;

/**
 * model层，数据的存取
 */
public class DataModel {
    //上拉刷新下拉加载更多
    public void onRefresh(boolean b, final DataModelCallBack modelCallBack){
        OkhttpUtils.getInstance().asy(null, "http://v.juhe.cn/toutiao/index?type=toutiao&key=c4479ad58f41e7f78a8fa073d0b1f1b5", new AbstractUiCallBack<MyDataBean>() {
            @Override
            public void success(MyDataBean dataBean) {
                modelCallBack.success(dataBean);
            }

            @Override
            public void failure(Exception e) {
                modelCallBack.failure(e);
            }
        });
    }

    //定义接口
    public interface DataModelCallBack{
        public void success(MyDataBean dataBean);   //成功获取数据
        public void failure(Exception e);            //数据获取失败
    }
}
