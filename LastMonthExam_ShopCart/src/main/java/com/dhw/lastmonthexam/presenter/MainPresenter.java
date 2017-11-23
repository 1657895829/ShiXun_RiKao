package com.dhw.lastmonthexam.presenter;
import com.dhw.lastmonthexam.bean.ShopBean;
import com.dhw.lastmonthexam.model.MainModel;
import com.dhw.lastmonthexam.model.MainModelCallBack;
import com.dhw.lastmonthexam.view.MainViewListener;

public class MainPresenter {
    private MainViewListener listener;
    private MainModel mainModel;
    public MainPresenter(MainViewListener listener){
        this.listener = listener ;
        this.mainModel = new MainModel();
    }


    public void getData(){


        mainModel.getData(new MainModelCallBack() {

            @Override
            public void success(ShopBean bean) {

                if(listener != null){
                    listener.success(bean);
                }

            }

            @Override
            public void failure(Exception e) {

                if(listener != null){
                    listener.failure(e);
                }
            }
        });
    }


    /**
     * 防止内存泄漏
     */
    public void detach(){
        listener = null;
    }



}
