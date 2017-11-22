package com.dhw.allselect.model;

import com.dhw.allselect.bean.Beans;
import java.util.List;

public interface IModel {
    void retro(Callback callback);
    interface Callback{
        void complate(List<Beans.SongListBean> list);
    }
}
