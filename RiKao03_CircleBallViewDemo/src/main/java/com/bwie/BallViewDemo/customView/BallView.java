package com.bwie.BallViewDemo.customView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * 自定义圆形小球view：手指在屏幕上滑动，红色的小球始终跟随手指移动。
 */
public class BallView extends View{
    private Paint paint;
    Context context;

    //圆的初始位置坐标
    private int x = 18;
    private int y = 18;
    private int radius = 188;    //圆半径

    /**
     * java代码创建时调用
     * @param context
     */
    public BallView(Context context) {
        super(context);
        this.context = context;
    }

    /**
     * xml创建时调用
     * @param context
     * @param attrs
     */
    public BallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    /**
     * 有style资源文件时调用
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public BallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    /**
     * 实现onDraw()方法实现绘图操作
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //用canvas将屏幕设为白色
        canvas.drawColor(Color.WHITE);

        //设置画笔颜色为红色
        paint = new Paint();
        paint.setColor(Color.RED);

        //设置消除锯齿
        paint.setAntiAlias(true);
        //使用画笔绘制圆为小球
        //x :圆心的x坐标
        //y :圆心的y坐标
        //radius ：圆的半径
        //paint ：画笔
        canvas.drawCircle(x,y,radius, paint);
    }

    //实现onTouchEvent方法，处理触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //判断触摸点
        switch (event.getAction()) {
            //实现MotionEvent.ACTION_DOWN，记录按下的x，y坐标：getRawX()和getRawY()获得的是相对屏幕的位置
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getX();
                y = (int) event.getY();
                System.out.println("按下时：  " + "x坐标：" + event.getRawX() + "     " + "y坐标：" + event.getRawY());

                //实现MotionEvent.ACTION_MOVE 记录移动的x，y坐标：getRawX()和getRawY()获得的是相对屏幕的位置
            case MotionEvent.ACTION_MOVE:
                x = (int) event.getX();
                y = (int) event.getY();
                System.out.println("移动时：  " + "x坐标：" + event.getRawX() + "     " + "y坐标：" + event.getRawY());

                //实现MotionEvent.ACTION_UP 记录抬起的x，y坐标
            case MotionEvent.ACTION_UP:
                // 获取当前触摸点的x,y坐标，为X轴和Y轴坐标重新赋值：getX()和getY()获得的永远是view的触摸位置坐标
                x = (int) event.getX();
                y = (int) event.getY();
                System.out.println("抬起时：  " + "x坐标：" + event.getRawX() + "     " + "y坐标：" + event.getRawY());
                break;
        }

        //获取屏幕宽高
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        int height = manager.getDefaultDisplay().getHeight();

        //修正圆点坐标，重新绘制圆 ,控制小球不会被移出屏幕
        if (x >= 18 && y >= 18 && x <= width - 18 && y <= height - 18) {
            /**
             *  Android提供了Invalidate方法实现界面刷新，但是Invalidate不能直接在线程中调用，因为他是违背了单线程模型：
             1. Android UI操作并不是线程安全的，并且这些操作必须在UI线程中调用。
             　　 invalidate()是用来刷新View的，必须是在UI线程中进行工作。比如在修改某个view的显示时，调用invalidate()才能看到重新绘制的界面。invalidate()的调用是把之前的旧的view从主UI线程队列中pop掉。
             2.Android 程序默认情况下也只有一个进程，但一个进程下却可以有许多个线程。在这么多线程当中，把主要是负责控
             制UI界面的显示、更新和控件交互的线程称为UI线程，由于onCreate()方法是由UI线程执行的，所以也可以把UI线程理解
             为主线程。其余的线程可以理解为工作者线程。invalidate()得在UI线程中被调动，在工作者线程中可以通过Handler来通
             知UI线程进行界面更新。而postInvalidate()在工作者线程中被调用。
             */
            //使用 postInvalidate()方法实现重绘小球，跟随手指移动
            postInvalidate();
        }
        /*
         * 备注：此处一定要将return super.onTouchEvent(event)修改为return true，原因是：
         * 1）父类的onTouchEvent(event)方法可能没有做任何处理，但是返回了false。
         * 2)一旦返回false，在该方法中再也不会收到MotionEvent.ACTION_MOVE及MotionEvent.ACTION_UP事件。
         */
        //return super.onTouchEvent(event);
        return true;
    }
}
