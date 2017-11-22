package com.example.product.presenter;
import android.content.Context;
import com.example.product.bean.Bean;
import com.example.product.model.DataModel;
import com.example.product.okhttp.OnUiCallback;
import com.example.product.view.DataView;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;

//Presenter层，交互view与model层的数据
public class DataPresenter {
    private Context context;
    private DataView dataView;
    DataModel dataModel;

    public DataPresenter(Context context, DataView dataView) {
        this.context = context;
        this.dataView = dataView;
        dataModel = new DataModel();
    }

    //获取网络数据
    public void showDataPresenter(int pid){
        dataModel.showData(pid, new OnUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {
            }

            @Override
            public void onSuccess(String result) {
                Bean bean = new Gson().fromJson(result, Bean.class);
                dataView.showView(bean);
            }
        });
    }
}
