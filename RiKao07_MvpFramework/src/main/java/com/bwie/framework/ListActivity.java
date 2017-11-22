package com.bwie.framework;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class ListActivity extends AppCompatActivity {

    @Bind(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.text)
    public void onViewClicked() {
        Intent intent = new Intent(ListActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
