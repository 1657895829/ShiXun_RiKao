package com.bwie.secondweek_moni.activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.bwie.secondweek_moni.R;
import butterknife.Bind;
import butterknife.ButterKnife;
//TextView详情页
public class FourthActivity extends AppCompatActivity {
    @Bind(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        ButterKnife.bind(this);

        String title = getIntent().getStringExtra("title");
        text.setText("歌曲："+title);
    }
}
