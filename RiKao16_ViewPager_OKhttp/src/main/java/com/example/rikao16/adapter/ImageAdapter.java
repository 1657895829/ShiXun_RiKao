package com.example.rikao16.adapter;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.rikao16.bean.Bean;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;

//无限轮播图适配器
public class ImageAdapter extends PagerAdapter {
    Context context;
    List<Bean.DataBean> list;
    public ImageAdapter(Context context, List<Bean.DataBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        //使用分割符获取下标为0的图片数据，设置为无限轮播图
        String images = list.get(0).getImages();
        String[] split = images.split("\\|");

        //无限加载图片数组中的图片
        ImageLoader.getInstance().displayImage(split[position%split.length],imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
