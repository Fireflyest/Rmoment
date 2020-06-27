package com.fireflyest.rmoment.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import androidx.annotation.Nullable;

@Deprecated
public class StraightBarView extends View {

    private Paint progressPaint;
    private Paint bgPaint;//绘制背景圆弧的画笔
    private ObjectAnimator animator;
    private int bgColor;//背景颜色
    private int progressColor;//进度条颜色
    private float radius;
    private float progressNum;
    private float maxNum;//进度条最大值
    private RectF rectFp;
    private RectF rectFb;
    private StraightBarAnim anim;

    public StraightBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        anim = new StraightBarAnim();
    }

    private void init(Context context,AttributeSet attrs) {
        //初始化画笔
        progressPaint = new Paint();
        progressPaint.setStyle(Paint.Style.STROKE);//只描边，不填充
        progressPaint.setAntiAlias(true);//设置抗锯齿

        bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.STROKE);//只描边，不填充
        bgPaint.setAntiAlias(true);//设置抗锯齿

        progressNum = 0;
        maxNum = 100;

        rectFp = new RectF(0, 0, progressNum, getHeight());
        rectFb = new RectF(0, 0, maxNum, getHeight());

//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StraightBarView);
////        progressColor = typedArray.getColor(R.styleable.StraightBarView_progress_color, Color.BLUE);//默认为蓝色
////        bgColor = typedArray.getColor(R.styleable.StraightBarView_bg_color,Color.GRAY);//默认为灰色
//        radius = typedArray.getInt(R.styleable.StraightBarView_radius,5);//默认为5
//        typedArray.recycle();//typedArray用完之后需要回收，防止内存泄漏

        progressPaint.setColor(progressColor);
        bgPaint.setColor(bgColor);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //圆角形状设置到画布
        canvas.drawRoundRect(rectFp, radius, radius, progressPaint);
        canvas.drawRoundRect(rectFb, radius, radius, bgPaint);
    }

    public class StraightBarAnim extends Animation{

        public StraightBarAnim(){

        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
        }
    }

    public void setProgressNum(float progressNum, int time){
        anim.setDuration(time);
        this.startAnimation(anim);
        this.progressNum = progressNum;
    }

    public void setProgressColor(int color){
        progressColor = color;
        progressPaint.setColor(progressColor);
    }

}
