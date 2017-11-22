package com.bwie.weekone_moni.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.bwie.weekone_moni.MainActivity;
import com.bwie.weekone_moni.R;

public class RectActivity  extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_rect);
    }

    //按钮点击调用的方法
    public void onBack(View view){
        Intent intent = new Intent(RectActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
