package com.example.thirdweek.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.thirdweek.R;
import com.example.thirdweek.activity.ShopActivity;
import com.example.thirdweek.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

//瀑布流界面适配器
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context context;
    private List<String> list;

    public ImageAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item, null);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage(list.get(position),holder.itemImage, ImageLoaderUtil.getDefaultOption());
        //单击条目图片跳转至购物车页面
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShopActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_image)
        ImageView itemImage;
        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
