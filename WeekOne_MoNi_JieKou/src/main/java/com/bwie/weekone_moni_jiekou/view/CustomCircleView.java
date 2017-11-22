package com.bwie.weekone_moni_jiekou.view;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.xys.libzxing.zxing.activity.CaptureActivity;

/**
 * 自定义圆环进度条，显示进度百分比
 * 点击扫描二维码按钮之后，进度条开始以每秒10%的进度进行，
 * 当进度条走到100%后，跳转到图三的扫描二维码页面，实现扫描二维码的功能
 */
public class CustomCircleView extends View{
    private int progress = 0;
    private Context context;
    private Paint paint;

    public CustomCircleView(Context context) {
        super(context);
    }

    public CustomCircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public CustomCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        //创建一支画笔
        paint = new Paint();
        //设置画笔的颜色
        paint.setColor(Color.BLUE);
        //设置画笔的style：内容是填充的空心圆
        paint.setStyle(Paint.Style.STROKE);
    }
    //执行进度条是耗时操作，需放在子线程中执行,进度达到100%后跳转至 扫描二维码 页面
    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (progress >= 360) {
                        Intent intent = new Intent(context, CaptureActivity.class);
                        context.startActivity(intent);
                        return;
                    }
                    progress += 10;

                    //子线程刷新 系统调用onDraw（） 方法
                    postInvalidate();

                    try {
                        Thread.sleep(188);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //（1）获取当前自定义view的宽高度
        int x = getWidth() / 2;
        int y = getHeight() / 2;

        //（2）设置圆半径的大小
        int radius = 200;

        //（3）设置画笔的粗细
        paint.setStrokeWidth(16);

        //（4）定义一个矩形区域：RectF对象持有一个矩形的四个float坐标值
        RectF rectF = new RectF(x- radius,y - radius,x + radius, y + radius);
        //画一个圆弧：drawArc方法：绘制圆弧，该方法用于在画布上绘制圆弧，通过指定圆弧所在的椭圆对象、起始角度、终止角度来实现。
        canvas.drawArc(rectF,-90,progress,false,paint);

        //（5）把progress转换为int值
        int intProgress = (int) ((float) progress / 360 * 100);

        //（6） measureText  测量字符串的宽度
        float textWidth = paint.measureText(intProgress + "%");

        //（7）定义一个矩形区域：Rect对象持有一个矩形的四个integer坐标值
        Rect rect = new Rect();
        //测量矩形中百分比的值的位置
        paint.getTextBounds(intProgress+"%",0,(intProgress+"%").length(),rect);

        //（8）设置画笔写出内容的size高度和粗细
        paint.setTextSize(28);
        paint.setStrokeWidth(1);

        //（9）画出文字  rect.height() 获取字符串的高度
        canvas.drawText(intProgress+"%",x-textWidth/2,y+textWidth/2,paint);
    }
}
