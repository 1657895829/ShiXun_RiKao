package com.bwie.weekone_moni;
import android.os.Bundle;
import com.bwie.weekone_moni.Activity.TitleActivity;
public class MainActivity extends TitleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("那些花儿");
        showBackwardView(R.string.text_back, true);
        showForwardView(R.string.text_forward, true);
    }
}
