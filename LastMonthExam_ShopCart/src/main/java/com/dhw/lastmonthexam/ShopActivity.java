package com.dhw.lastmonthexam;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.dhw.lastmonthexam.bean.ShopBean;
import com.dhw.lastmonthexam.presenter.MainPresenter;
import com.dhw.lastmonthexam.view.MainViewListener;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopActivity extends AppCompatActivity implements MainViewListener {
    @Bind(R.id.third_recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.third_allselect)
    CheckBox checkBoxAll;
    @Bind(R.id.third_totalprice)
    TextView AllPrice;
    @Bind(R.id.third_totalnum)
    TextView allNum;
    private MainPresenter presenter;
    private ShopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
        presenter.getData();

        //设置布局管理器及适配器
        adapter = new ShopAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(adapter);

        adapter.setListener(new ShopAdapter.UpdateUiListener() {
            @Override
            public void setTotal(String total, String num, boolean allCheck) {
                checkBoxAll.setChecked(allCheck);
                allNum.setText("共计："+num+" 件");
                AllPrice.setText("总价：￥"+total);
            }
        });
    }

    @Override
    public void success(ShopBean bean) {
        adapter.add(bean);
    }

    @Override
    public void failure(Exception e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ShopActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @OnClick(R.id.third_allselect)
    public void onViewClicked() {
        adapter.selectAll(checkBoxAll.isChecked());
    }
}
