package com.bwie.weekone_moni_jiekou.view;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.bwie.weekone_moni_jiekou.R;

/**
 * 自定义组合控件实现标题栏
 */
public class TitleView extends LinearLayout implements View.OnClickListener{
    private Context conText;
    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        conText = context;

        //将自定义组合控件布局按钮id填充到当前页面
        View view = LayoutInflater.from(context).inflate(R.layout.title_layout, this, true);
        Button button_backward = view.findViewById(R.id.button_backward);
        Button button_forward = view.findViewById(R.id.button_forward);
        button_forward.setOnClickListener(this);
        button_backward.setOnClickListener(this);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View view) {
        //调用接口的方法
        titleViewClickListener.titleViewButtonClicked(view);
    }

    //自定义 接口的成员
    OnTitleViewClickListener titleViewClickListener;

    //自定义 实现按钮点击事件的接口
    public interface  OnTitleViewClickListener{
        public void titleViewButtonClicked(View view);  //View view:传的参数
    }

    //使用setter设置接口回调的声明变量
    public void setOnTitleViewClickListener(OnTitleViewClickListener titleViewClickListener){
        //在setter中把这个接口的实现 赋值给这个TitleView类上面实现的接口
        this.titleViewClickListener = titleViewClickListener;
    }
}
