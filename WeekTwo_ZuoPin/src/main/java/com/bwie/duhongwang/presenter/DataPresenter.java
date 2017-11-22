package com.bwie.duhongwang.presenter;
import android.content.Context;
import android.util.Log;
import com.bwie.duhongwang.bean.ProductsBean;
import com.bwie.duhongwang.model.MyDataModel;
import com.bwie.duhongwang.okhttp.OnUiCallback;
import com.bwie.duhongwang.view.DataView;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;
/**
 * presenter层，进行model和view层之间数据的交互
 */
public class DataPresenter {
    Context context;
    private DataView dataView;
    private MyDataModel dataModel;
    //构造方法中声明view层，初始化model层数据,将 mvp三层关联
    public DataPresenter(Context context, DataView dataView) {
        this.context = context;
        this.dataView = dataView;
        dataModel = new MyDataModel();
    }

    //调用model层接口，上拉加载下拉刷新
    public void getData(String keyword,String page){
        dataModel.getData(keyword, page, new OnUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {
                //数据获取//失败方法
            }

            @Override
            public void onSuccess(String result) {
                //获取数据成功时将放回的json 变成bean
                Log.i("返回数据", "结果: "+result.toString());
                ProductsBean bean = new Gson().fromJson(result, ProductsBean.class);
                dataView.showView(bean);
            }
        });
    }

    //用来防止内存溢出
    public void destory(){
        this.dataView = null;
    }
}
