package com.dhw.lastmonthexam.presenter;

import com.dhw.lastmonthexam.bean.SongBean;
import com.dhw.lastmonthexam.model.MyModel;
import com.dhw.lastmonthexam.view.MyView;

//presenter层
public class MyPresenter {
    private MyView  view;
    private MyModel model;

    public MyPresenter(MyView view) {
        this.view = view;
        model = new MyModel();
    }

    public void onRefresh(boolean up){
        model.onRefresh(up, new MyModel.ModelCallBack() {
            @Override
            public void success(SongBean songBean) {
                view.success(songBean);
            }

            @Override
            public void failure(Exception e) {
                view.failure(e);
            }
        });
    }
}
