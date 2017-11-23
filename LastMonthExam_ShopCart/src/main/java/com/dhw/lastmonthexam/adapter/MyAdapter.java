package com.dhw.lastmonthexam.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.dhw.lastmonthexam.R;
import com.dhw.lastmonthexam.ShopActivity;
import com.dhw.lastmonthexam.bean.SongBean;
import com.dhw.lastmonthexam.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

//布局适配器
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> {
    private Context context;
    private List<SongBean.SongListBean> list;

    public MyAdapter(Context context) {
        this.context = context;
    }

    //添加数据的方法
    public void add(List<SongBean.SongListBean> list) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        ImageLoader.getInstance().displayImage(list.get(position).getPic_radio(),holder.image, ImageLoaderUtil.getDefaultOption());
        holder.title.setText(list.get(position).getTitle());
        holder.author.setText(list.get(position).getAuthor()+"      "+list.get(position).getTitle());

        //条目单击事件,点击条目跳转到购物车页面
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onClick(v,position);
                }
                Intent intent = new Intent(context, ShopActivity.class);
                context.startActivity(intent);
                Toast.makeText(context,"当前单击条目为："+position,Toast.LENGTH_SHORT).show();
            }
        });

        //条目长按事件
        holder.image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener != null){       //回调接口，显示数据
                    onItemClickListener.onLongClick(v,position);
                }
                Toast.makeText(context,"当前长按条目为："+position,Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.author)
        TextView author;
        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    //再声明接口变量，设置接口的setter方法
    private onItemClickListener onItemClickListener;
    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //1.先条目的点击接口回调事件
    public interface onItemClickListener{
        public void onClick(View view,int position);        //单击事件
        public void onLongClick(View view,int position);    //长按事件
    }
}
