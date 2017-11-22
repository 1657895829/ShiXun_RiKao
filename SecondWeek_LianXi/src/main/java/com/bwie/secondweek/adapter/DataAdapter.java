package com.bwie.secondweek.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bwie.secondweek.R;
import com.bwie.secondweek.bean.MyDataBean;
import com.bwie.secondweek.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 条目布局适配器
 */
public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<MyDataBean.ResultBean.DataBean> list;

    public DataAdapter(Context context) {
        this.context = context;
    }

    //声明数据来源，添加数据
    public void addData(List<MyDataBean.ResultBean.DataBean> list) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    //创建条目布局
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view01 = LayoutInflater.from(context).inflate(R.layout.item1, parent, false);
            return new ViewHolder01(view01);
        } else if (viewType == 1) {
            View view02 = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
            return new ViewHolder02(view02);
        } else {
            View view03 = LayoutInflater.from(context).inflate(R.layout.item3, parent, false);
            return new ViewHolder03(view03);
        }
    }

    //绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //判断加载布局的类型
        if (holder instanceof ViewHolder01) {
            ViewHolder01 holder01 = (ViewHolder01) holder;
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(), holder01.listItem1Imageview, ImageLoaderUtil.getDefaultOption());
        } else if (holder instanceof ViewHolder02) {
            ViewHolder02 holder02 = (ViewHolder02) holder;
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(), holder02.listItem2Imageview1, ImageLoaderUtil.getDefaultOption());
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s02(), holder02.listItem2Imageview2, ImageLoaderUtil.getDefaultOption());
        } else {
            ViewHolder03 holder03 = (ViewHolder03) holder;
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(), holder03.listItem3Imageview1, ImageLoaderUtil.getDefaultOption());
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s02(), holder03.listItem3Imageview2, ImageLoaderUtil.getDefaultOption());
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s03(), holder03.listItem3Imageview3, ImageLoaderUtil.getDefaultOption());
        }
    }

    @Override
    public int getItemViewType(int position) {
        //判断布局类型，加载不同数据
        if (!TextUtils.isEmpty(list.get(position).getThumbnail_pic_s03())) {
            return 2;
        }else if (!TextUtils.isEmpty(list.get(position).getThumbnail_pic_s02())) {
            return 1;
        }else {
            return 0;
        }
    }

    //首先加载三种图片布局
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHolder01 extends RecyclerView.ViewHolder {
        @Bind(R.id.list_item1_imageview)
        ImageView listItem1Imageview;

        ViewHolder01(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ViewHolder02 extends RecyclerView.ViewHolder {
        @Bind(R.id.list_item2_imageview1)
        ImageView listItem2Imageview1;
        @Bind(R.id.list_item2_imageview2)
        ImageView listItem2Imageview2;
        ViewHolder02(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ViewHolder03 extends RecyclerView.ViewHolder {
        @Bind(R.id.list_item3_imageview1)
        ImageView listItem3Imageview1;
        @Bind(R.id.list_item3_imageview2)
        ImageView listItem3Imageview2;
        @Bind(R.id.list_item3_imageview3)
        ImageView listItem3Imageview3;
        ViewHolder03(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}