package com.dhw.allselect.presenter;
import android.content.Context;
import com.dhw.allselect.bean.Beans;
import com.dhw.allselect.model.IModel;
import com.dhw.allselect.model.ShowModel;
import com.dhw.allselect.view.Iview;
import java.util.List;

public class Presenters {
    Context context;
    IModel model;
    Iview view;

    public Presenters(Context context, Iview view) {
        this.context = context;
        this.view = view;
        model=new ShowModel();
    }

    public void getData(){
        model.retro(new IModel.Callback() {
            @Override
            public void complate(List<Beans.SongListBean> list) {
                view.setData(list);
            }
        });
    }
}
