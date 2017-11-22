package com.dhw.allselect.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.dhw.allselect.R;
import com.dhw.allselect.bean.Beans;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    List<Beans.SongListBean> list;
    private boolean isShow = false;
    private boolean isSelect = false;
    public MyAdapter(Context context, List<Beans.SongListBean> list, boolean isShow, boolean isSelect) {
        this.context = context;
        this.list = list;
        this.isShow = isShow;
        this.isSelect = isSelect;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(context, R.layout.item,null));
    }
    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        if (isShow) {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(isSelect);
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }
        holder.author.setText(list.get(position).getTitle());
        holder.ting_uid1.setText(list.get(position).getArtist_name());
        DraweeController dc= Fresco.newDraweeControllerBuilder()
                .setUri(list.get(position).getPic_big())
                .setAutoPlayAnimations(true)
                .build();
        holder.imageView.setController(dc);
    }
    @Override
    public int getItemCount() {
        return list.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView author,ting_uid1,ting_uid2,ting_uid3;
        SimpleDraweeView imageView;
        CheckBox checkBox;
        public MyViewHolder(View itemView) {
            super(itemView);
            author= itemView.findViewById(R.id.author);
            ting_uid1= itemView.findViewById(R.id.ting_uid1);
            ting_uid2= itemView.findViewById(R.id.ting_uid2);
            ting_uid3=  itemView.findViewById(R.id.ting_uid3);
            imageView= itemView.findViewById(R.id.imageView);
            checkBox= itemView.findViewById(R.id.checkBox);
        }
    }
}
