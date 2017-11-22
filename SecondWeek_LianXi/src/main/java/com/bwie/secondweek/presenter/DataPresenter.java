package com.bwie.secondweek.presenter;
import com.bwie.secondweek.bean.MyDataBean;
import com.bwie.secondweek.model.DataModel;
import com.bwie.secondweek.view.DataView;

/**
 * presenter层，进行model和view层之间数据的交互
 */
public class DataPresenter {
    private DataView dataView;
    private DataModel dataModel;

    //构造方法中声明view层，初始化model层数据
    public DataPresenter(DataView dataView) {
        this.dataView = dataView;
        dataModel = new DataModel();
    }

    //调用model层接口，上拉加载下拉刷新
    public void onRefresh(final boolean b){
        dataModel.onRefresh(b, new DataModel.DataModelCallBack() {
            @Override
            public void success(MyDataBean dataBean) {
                dataView.success(dataBean);
            }

            @Override
            public void failure(Exception e) {
                dataView.failure(e);
            }
        });
    }
}
