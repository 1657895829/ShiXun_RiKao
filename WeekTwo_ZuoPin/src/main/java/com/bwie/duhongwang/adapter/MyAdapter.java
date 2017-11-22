package com.bwie.duhongwang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.duhongwang.R;
import com.bwie.duhongwang.bean.ProductsBean;
import com.bwie.duhongwang.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

//自定义适配器
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ProductsBean productsBean;

    public MyAdapter(Context context, ProductsBean productsBean) {
        this.context = context;
        this.productsBean = productsBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //设置条目信息
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        viewHolder.title.setText("标题： "+productsBean.getData().get(position).getTitle() );
        viewHolder.price.setText("价格： "+productsBean.getData().get(position).getPrice() );
        String images = productsBean.getData().get(position).getImages();
        String[] split = images.split("\\|");

        //使用Glide加载图片容易闪烁，错位，效果不如imageloader好
        //Glide.with(context).load(split[0]).into(viewHolder.image);
        ImageLoader.getInstance().displayImage(split[0], viewHolder.image, ImageLoaderUtil.getDefaultOption());
    }

    @Override
    public int getItemCount() {
        return productsBean.getData() == null ? 0 : productsBean.getData().size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.price)
        TextView price;
        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
