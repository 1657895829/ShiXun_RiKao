package com.dhw.allselect.model;
import android.util.Log;
import com.dhw.allselect.bean.Beans;
import com.dhw.allselect.util.URLUtils;
import java.util.List;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShowModel implements IModel{

    @Override
    public void retro(final Callback callback) {
        URLUtils.utils.getObservable().observeOn(AndroidSchedulers.mainThread())
                //设置被观察者在子线程
                .subscribeOn(Schedulers.io())
                //订阅观察者
                .subscribe(new Observer<Beans>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("==error==", "onNext: "+e);
                    }

                    @Override
                    public void onNext(Beans beans) {

                        List<Beans.SongListBean> list = beans.getSong_list();
                        callback.complate(list);
                        Log.i("====", "onNext: "+list);

                    }
                });
    }
}
