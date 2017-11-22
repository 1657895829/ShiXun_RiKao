package com.example.thirdweek;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;
import com.example.thirdweek.adapter.ImageAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

//RecyclerView瀑布流页面
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //定义图片路径
        List<String> list = new ArrayList<>();
        list.add("http://pic1.win4000.com/wallpaper/c/56d8e47550b86_270_185.jpg");
        list.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3437523560,2570312276&fm=27&gp=0.jpg");
        list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1048304009,1533562216&fm=27&gp=0.jpg");
        list.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3744146906,172628736&fm=27&gp=0.jpg");
        list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=900946121,4058384925&fm=27&gp=0.jpg");
        list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2746401840,2463337952&fm=27&gp=0.jpg");
        list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4009572336,3320118404&fm=27&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=2710025438,2030209695&fm=27&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=2749800578,1172713594&fm=27&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=1364748988,3595630243&fm=27&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=1313667106,1509125836&fm=27&gp=0.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510976059196&di=50de833dcb98d1cd16ef0b46342ee861&imgtype=0&src=http%3A%2F%2Fpic23.photophoto.cn%2F20120530%2F0020033092420808_b.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510976059192&di=dc21c71929dfa6ff74596baabd04e1b0&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3Dcfb53f93c3177f3e0439f44e18a651b2%2F6609c93d70cf3bc7814060c9db00baa1cd112a56.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=4166721891,1503444760&fm=27&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=2749800578,1172713594&fm=27&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=2913668656,2233921135&fm=200&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=4273540577,2947670665&fm=27&gp=0.jpg");
        list.add("http://pic.58pic.com/58pic/15/41/77/58D58PICWbe_1024.jpg");
        list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1599331581,940426819&fm=27&gp=0.jpg");
        list.add("http://pic6.nipic.com/20100415/1954049_161502002607_2.jpg");
        list.add("http://img2.3lian.com/2014/f5/60/d/98.jpg");
        list.add("http://pic19.nipic.com/20120223/3693935_220314428359_2.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=2541175338,1882921044&fm=27&gp=0.jpg");
        list.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1118952244,3002613512&fm=27&gp=0.jpg");

        //设置瀑布流布局管理器和适配器
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ImageAdapter adapter = new ImageAdapter(MainActivity.this,list);
        recyclerView.setAdapter(adapter);
    }
}
