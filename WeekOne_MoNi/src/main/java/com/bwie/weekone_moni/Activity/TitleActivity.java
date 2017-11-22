package com.bwie.weekone_moni.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bwie.weekone_moni.R;
import com.bwie.weekone_moni.View.CustomCircleView;
/**
 * 定义TitleActivity布局以及按钮
 * 标题栏通过组合View的方式进行实现，统一对外暴露左侧按钮和右侧按钮点击的方法
 */
public class TitleActivity extends Activity implements View.OnClickListener {
    private TextView mTitleTextView;
    private Button mBackwardbButton;
    private Button mForwardButton;
    private FrameLayout mContentLayout;
    private CustomCircleView progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();   //加载 activity_title 布局 ，并获取标题及两侧按钮
    }

    private void setupViews() {
        super.setContentView(R.layout.activity_title);

        mTitleTextView = (TextView) findViewById(R.id.text_title);
        progress = findViewById(R.id.custom_Circle);
        mContentLayout = (FrameLayout) findViewById(R.id.layout_content);
        mBackwardbButton = (Button) findViewById(R.id.button_backward);
        mForwardButton = (Button) findViewById(R.id.button_forward);
    }

    /**
     * 是否显示返回按钮
     * @param backwardResid 文字
     * @param show          true则显示
     */
    protected void showBackwardView(int backwardResid, boolean show) {
        if (mBackwardbButton != null) {
            if (show) {
                mBackwardbButton.setText(backwardResid);
                mBackwardbButton.setVisibility(View.VISIBLE);
            } else {
                mBackwardbButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 提供是否显示提交按钮
     * @param forwardResId 文字
     * @param show         true则显示
     */
    protected void showForwardView(int forwardResId, boolean show) {
        if (mForwardButton != null) {
            if (show) {
                mForwardButton.setVisibility(View.VISIBLE);
                mForwardButton.setText(forwardResId);
            } else {
                mForwardButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 返回按钮点击后触发
     * @param backwardView
     */
    protected void onBackward(View backwardView) {
        Toast.makeText(this, "点击退出程序，可在此处调用finish()", Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * 提交按钮点击后触发
     * @param forwardView
     */
    protected void onForward(View forwardView) {
        Toast.makeText(this, "点击我跳转到矩形view", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(TitleActivity.this, RectActivity.class);
        startActivity(intent);
    }

    //设置标题内容
    @Override
    public void setTitle(int titleId) {
        mTitleTextView.setText(titleId);
    }

    //设置标题内容
    @Override
    public void setTitle(CharSequence title) {
        mTitleTextView.setText(title);
    }

    //设置标题文字颜色
    @Override
    public void setTitleColor(int textColor) {
        mTitleTextView.setTextColor(textColor);
    }

    //取出FrameLayout并调用父类removeAllViews()方法
    @Override
    public void setContentView(int layoutResID) {
        mContentLayout.removeAllViews();
        View.inflate(this, layoutResID, mContentLayout);
        onContentChanged();
    }

    @Override
    public void setContentView(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
        onContentChanged();
    }

    /*
     * @see android.app.Activity#setContentView(android.view.View, android.view.ViewGroup.LayoutParams)
     */
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view, params);
        onContentChanged();
    }

    /*
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     * 按钮点击调用的方法
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_backward:
                onBackward(v);
                break;

            case R.id.button_forward:
                onForward(v);
                break;

            default:
                break;
        }
    }

    //点击按钮扫描二维码
    public void saoMiaoCode(View view) {
        progress.start();
    }
}