package com.bwie.lastmonth.view;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.bwie.lastmonth.R;
/**
 * Created by Administrator on 2017/11/5.
 */
public class CustomCircleView extends View {
    private final static String TAG = "RoundProgressBar01";
    private Paint paint;
    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private float textSize;
    private float roundWidth;
    private int max;
    private int progress;
    private boolean textIsDisplayable;
    private int style;
    public static final int STROKE = 0;
    public static final int FILL = 1;
    public CustomCircleView(Context context) {
        super(context);
        init();
    }
    public CustomCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        roundColor = mTypedArray.getColor(R.styleable.MyView_roundColor, Color.RED);
        roundProgressColor = mTypedArray.getColor(R.styleable.MyView_roundProgressColor, Color.GREEN);
        textColor = mTypedArray.getColor(R.styleable.MyView_textColor, Color.GREEN);
        textSize = mTypedArray.getDimension(R.styleable.MyView_textSize, 15);
        roundWidth = mTypedArray.getDimension(R.styleable.MyView_roundWidth, 20);
        max = mTypedArray.getInteger(R.styleable.MyView_max, 100);
        textIsDisplayable = mTypedArray.getBoolean(R.styleable.MyView_textIsDisplayable, true);
        style = mTypedArray.getInt(R.styleable.MyView_style, 0);
        mTypedArray.recycle();
    }

    public CustomCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        paint = new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centre = getWidth()/2; //获取圆心的x坐标
        int radius = (int) (centre - roundWidth/2); //圆环的半径
        paint.setColor(roundColor); //设置圆环的颜色
        paint.setStyle(Paint.Style.STROKE); //设置空心
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度
        paint.setAntiAlias(true);  //消除锯齿
        canvas.drawCircle(centre, centre, radius, paint); //画出圆环

        /**
         * 画进度百分比
         */
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD); //设置字体
        //中间的进度百分比，先转换成float在进行除法运算，不然都为0
        int percent = (int)(((float)progress / (float)max) * 100);
        //测量字体宽度，我们需要根据字体的宽度设置在圆环中间
        float textWidth = paint.measureText(percent + "%");
        Log.i(TAG, "onDraw:"+"textIsDisplayable:"+textIsDisplayable+"--percent:"+percent+"--style == STROKE:"+(style == STROKE));
        //if(textIsDisplayable && percent != 0 && style == STROKE){
        if(textIsDisplayable && style == STROKE){
            //画出进度百分比
            canvas.drawText(percent + "%", centre - textWidth / 2, centre + textSize/2, paint);
        }

        /**
         * 画圆弧 ，画圆环的进度
         */

        //设置进度是实心还是空心
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度
        paint.setColor(roundProgressColor);  //设置进度的颜色
        //用于定义的圆弧的形状和大小的界限
        RectF oval = new RectF(centre - radius, centre - radius, centre+ radius, centre + radius);
        switch (style) {
            case STROKE:{
                paint.setStyle(Paint.Style.STROKE);
                //根据进度画圆弧
                canvas.drawArc(oval, 0, 360 * progress / max, false, paint);
                break;
            }
            case FILL:{
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if(progress !=0)
                    //根据进度画圆弧
                    canvas.drawArc(oval, 0, 360 * progress / max, true, paint);
                break;
            }
        }
    }

    public synchronized int getMax() {
        return max;
    }
    public synchronized void setMax(int max) {
        if(max < 0){
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }
    public synchronized int getProgress() {
        return progress;
    }

    public synchronized void setProgress(int progress) {
        if(progress < 0){
            throw new IllegalArgumentException("progress not less than 0");
        }
        if(progress > max){
            progress = max;
        }
        if(progress <= max){
            this.progress = progress;
            postInvalidate();
        }
    }

    public int getCricleColor() {
        return roundColor;
    }
    public void setCricleColor(int cricleColor) {
        this.roundColor = cricleColor;
    }
    public int getCricleProgressColor() {
        return roundProgressColor;
    }
    public void setCricleProgressColor(int cricleProgressColor) {
        this.roundProgressColor = cricleProgressColor;
    }
    public int getTextColor() {
        return textColor;
    }
    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
    public float getTextSize() {
        return textSize;
    }
    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }
    public float getRoundWidth() {
        return roundWidth;
    }
    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }
    public synchronized void setStyle(int style){
        this.style = style;
    }
    //改变颜色的方法
    public void setMyColor(int color){
        roundColor = color;
        invalidate();
    }
}
