package com.dhw.three.presenter;

import com.dhw.three.bean.DingdanBean;
import com.dhw.three.callback.MyViewCallBack;
import com.dhw.three.model.MyModel;

//presenter层
public class MyPresenter {
    MyModel myModel = new MyModel();
    MyViewCallBack myViewCallBack;
    public MyPresenter(MyViewCallBack myViewCallBack) {
        this.myViewCallBack = myViewCallBack;
    }

    //调用model层去访问数据
    public void getDataFromModel(int page){
        myModel.getData(page,new MyModel.ModelCallBack() {
            @Override
            public void success(DingdanBean dingdanBean) {
                myViewCallBack.viewSuccess(dingdanBean);
            }

            @Override
            public void fail(Exception e) {
                myViewCallBack.viewFail(e);
            }
        });
    }

    //调用model层去访问数据
    public void popUpQingqiu(int status){
        myModel.popUpGetData(status,new MyModel.ModelCallBack() {
            @Override
            public void success(DingdanBean dingdanBean) {
                myViewCallBack.viewSuccess(dingdanBean);
            }

            @Override
            public void fail(Exception e) {
                myViewCallBack.viewFail(e);
            }
        });
    }

    //防止内存泄露
    public void detach(){
        this.myViewCallBack = null;
    }
}

