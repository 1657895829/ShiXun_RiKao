package com.bwie.secondweek_moni.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.secondweek_moni.R;
import com.bwie.secondweek_moni.activity.FourthActivity;
import com.bwie.secondweek_moni.bean.DataBean;
import com.bwie.secondweek_moni.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

//自定义适配器类
public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<DataBean.SongListBean> list;

    public DataAdapter(Context context) {
        this.context = context;
    }

    //声明数据来源，添加数据
    public void addData(List<DataBean.SongListBean> songList) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        this.list.addAll(songList);
        notifyDataSetChanged();
    }

    //创建条目布局
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, null);
        return new ItemViewHolder(view);
    }

    //绑定数据
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        //设置条目信息
        final ItemViewHolder viewHolder = (ItemViewHolder) holder;
        viewHolder.text01.setText("歌曲： " + list.get(position).getTitle());
        viewHolder.text02.setText("歌手： " + list.get(position).getAuthor());
        ImageLoader.getInstance().displayImage(list.get(position).getPic_premium(), viewHolder.image, ImageLoaderUtil.getDefaultOption());

        //添加RecyclerView的单击事件,点击RecyclerView控件后把对应条目的标题传到详情Activity并显示（详情页效果图未提供，里面就一个TextView，接收到数据后，给TextView设置值即可）
        ((ItemViewHolder) holder).text01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FourthActivity.class);
                intent.putExtra("title",list.get(position).getTitle());
                context.startActivity(intent);
            }
        });

        //添加RecyclerView的长按事件，长按删除该条目
        ((ItemViewHolder) holder).text02.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                list.remove(position);
                Toast.makeText(context,"长按删除当前条目",Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.text01)
        TextView text01;
        @Bind(R.id.text02)
        TextView text02;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}