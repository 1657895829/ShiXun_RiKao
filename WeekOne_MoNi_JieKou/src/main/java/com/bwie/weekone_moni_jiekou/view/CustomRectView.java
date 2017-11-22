package com.bwie.weekone_moni_jiekou.view;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义3个首尾相连的矩形view
 */
public class CustomRectView extends View{
    public CustomRectView(Context context) {
        super(context);
    }
    public CustomRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public CustomRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //(1)设置画笔的size
        Paint paint1 = new Paint();
        paint1.setTextSize(10);
        //(2)设置画笔的颜色
        paint1.setColor(Color.BLUE);
        //(3)drawRect 就是使用画笔绘制一个矩形 前面两个参数代表起始坐标， 也就是左上角 后面两个参数可以标识你想画的长度和宽度 也可以理解为右下角的坐标点。
        Rect rect = new Rect(0,0,350,85);
        canvas.drawRect(rect, paint1);

        //(1)设置画笔的size
        Paint paint2 = new Paint();
        paint2.setTextSize(10);
        //(2)设置画笔的颜色
        paint2.setColor(Color.GREEN);
        //(3)drawRect 就是使用画笔绘制一个矩形 前面两个参数代表起始坐标， 也就是左上角 后面两个参数可以标识你想画的长度和宽度 也可以理解为右下角的坐标点。
        Rect rect2 = new Rect(350,80,730,160);
        canvas.drawRect(rect2, paint2);

        //(1)设置画笔的size
        Paint paint3 = new Paint();
        paint3.setTextSize(10);
        //(2)设置画笔的颜色
        paint3.setColor(Color.RED);
        //(3)drawRect 就是使用画笔绘制一个矩形 前面两个参数代表起始坐标， 也就是左上角 后面两个参数可以标识你想画的长度和宽度 也可以理解为右下角的坐标点。
        Rect rect3 = new Rect(730,160,1080,250);
        canvas.drawRect(rect3, paint3);
    }
}
