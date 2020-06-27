package com.fireflyest.rmoment.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.fireflyest.rmoment.R;

public class CircleBarView extends View {

    private Paint progressPaint;//绘制圆弧的画笔
    private float sweepAngle;//圆弧经过的角度
    private Paint bgPaint;//绘制背景圆弧的画笔

    private float progressNum;//可以更新的进度条数值
    private float maxNum;//进度条最大值

    private float progressSweepAngle;//进度条圆弧扫过的角度
    private float startAngle;//背景圆弧的起始角度
    private RectF mRectF;//绘制圆弧的矩形区域

    private float barWidth;//圆弧进度条宽度
    private int defaultSize;//自定义View默认的宽高
    private int progressColor;//进度条圆弧颜色
    private int bgColor;//背景圆弧颜色
    private CircleBarAnim anim;

    public CircleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
        anim = new CircleBarAnim();
    }

    private void init(Context context,AttributeSet attrs){

        progressPaint = new Paint();
        progressPaint.setStyle(Paint.Style.STROKE);//只描边，不填充
        progressPaint.setAntiAlias(true);//设置抗锯齿

        bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.STROKE);//只描边，不填充
        bgPaint.setAntiAlias(true);//设置抗锯齿

        progressNum = 0;
        maxNum = 100;

        DpOrPxUtils dpOrPxUtils = new DpOrPxUtils();
        defaultSize = dpOrPxUtils.dip2px(context,100);
        barWidth = dpOrPxUtils.dip2px(context,10);
        mRectF = new RectF();

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CircleBarView);
        progressColor = typedArray.getColor(R.styleable.CircleBarView_progress_color,Color.BLUE);//默认为蓝色
        bgColor = typedArray.getColor(R.styleable.CircleBarView_bg_color,Color.GRAY);//默认为灰色
        startAngle = typedArray.getFloat(R.styleable.CircleBarView_start_angle,0);//默认为0
        sweepAngle = typedArray.getFloat(R.styleable.CircleBarView_sweep_angle,360);//默认为360
        barWidth = typedArray.getDimension(R.styleable.CircleBarView_bar_width,dpOrPxUtils.dip2px(context,10));//默认为10dp
        typedArray.recycle();//typedArray用完之后需要回收，防止内存泄漏

        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setColor(progressColor);
        progressPaint.setStrokeWidth(barWidth);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        bgPaint.setColor(bgColor);
        bgPaint.setStrokeWidth(barWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = measureSize(defaultSize, heightMeasureSpec);
        int width = measureSize(defaultSize, widthMeasureSpec);
        int min = Math.min(width, height);// 获取View最短边的长度
        setMeasuredDimension(min, min);// 强制改View为以最短边为长度的正方形

        if(min >= barWidth*2){//这里简单限制了圆弧的最大宽度
            mRectF.set(barWidth/2,barWidth/2,min-barWidth/2,min-barWidth/2);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(mRectF,startAngle,sweepAngle,false,bgPaint);
        canvas.drawArc(mRectF,startAngle,progressSweepAngle,false, progressPaint);
    }

    public class CircleBarAnim extends Animation {

        public CircleBarAnim(){
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            progressSweepAngle = interpolatedTime * sweepAngle * progressNum / maxNum;//这里计算进度条的比例
            postInvalidate();
        }
    }

    private int measureSize(int defaultSize,int measureSpec) {
        int result = defaultSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(result, specSize);
        }
        return result;
    }

    public class DpOrPxUtils {
        public int dip2px(Context context, float dpValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }
        public int px2dip(Context context, float pxValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
        }
    }

    public void setProgressNum(float progressNum, int time) {
        anim.setDuration(time);
        this.startAnimation(anim);
        this.progressNum = progressNum;
    }

    public void setProgressColor(int color){
        progressColor = color;
        progressPaint.setColor(progressColor);
    }
}