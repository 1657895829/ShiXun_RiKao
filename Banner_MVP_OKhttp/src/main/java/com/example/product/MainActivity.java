package com.example.product;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.product.bean.Bean;
import com.example.product.presenter.DataPresenter;
import com.example.product.util.ImageLoaderUtil;
import com.example.product.view.DataView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

//主功能代码，实现view层
public class MainActivity extends AppCompatActivity implements DataView {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.yuanJia)
    TextView yuanJia;
    @Bind(R.id.youHui)
    TextView youHui;
    @Bind(R.id.banner)
    Banner banner;
    private List<String> list = new ArrayList<>();
    private DataPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //使用p层交互数据
        presenter = new DataPresenter(MainActivity.this, MainActivity.this);
        //传递pid数据
        presenter.showDataPresenter(12);
    }

    @Override
    public void showView(Bean bean) {
        //获取图片数据源
        String images = bean.getData().getImages();
        String[] split = images.split("\\|");

        //添加到新的集合中
        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
        }

        //设置图片自动轮播
        banner.isAutoPlay(true);
        //将图片集合放入banner中，加载图片
        banner.setImages(list);
        //设置图片每 3 秒切换
        banner.setDelayTime(3000);
        //加载图片
        banner.setImageLoader(new ImageLoaderUtil());

        /**
         * 设置轮播图小圆点样式，不设置下面代码默认小圆点在底部居中
         * 下面代码都是小圆点在底部居中显示
         */
        //banner.setBannerStyle(Banner.ACCESSIBILITY_LIVE_REGION_ASSERTIVE);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        //开始执行banner轮播
        banner.start();

        //设置商品标题，价格
        title.setText(bean.getData().getTitle());
        yuanJia.setText("原价：￥" + bean.getData().getPrice());
        youHui.setText("优惠价：￥" + bean.getData().getBargainPrice());

        /**
         * 文字线条的显示样式：
         * getPaint().setAntiAlias(true);// 抗锯齿
         * getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
         * getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中间横线（删除线）
         */
        yuanJia.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中间横线（删除线）
    }
}