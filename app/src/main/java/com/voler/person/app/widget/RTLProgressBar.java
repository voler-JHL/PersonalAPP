package com.voler.person.app.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

import com.voler.person.app.R;

/**
 * 三尺春光驱我寒，一生戎马为长安
 * Created by Han on 17/9/7.
 */

public class RTLProgressBar extends ProgressBar {

    private static final int DEFAULT_TEXT_SIZE = 10;
    private static final int DEFAULT_TEXT_COLOR = 0XFFFC00D1;
    private static final int DEFAULT_BACKGROUD_COLOR = 0xFFd3d6da;
    private static final int DEFAULT_HEIGHT = 20;
    private static final int DEFAULT_SIZE_TEXT_OFFSET = 10;

    protected Paint mPaint = new Paint();
    protected int mTextColor = DEFAULT_TEXT_COLOR;
    protected float mTextSize;
    protected int mTextOffset;
    protected float mProgressHeight;
    protected int mReachedBarColor = DEFAULT_TEXT_COLOR;
    protected int mBackgroudColor = DEFAULT_BACKGROUD_COLOR;
    /**
     * view width except padding
     */
    protected int mRealWidth;


    public RTLProgressBar(Context context) {
        this(context, null);
    }

    public RTLProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RTLProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setHorizontalScrollBarEnabled(true);

        obtainStyledAttributes(context, attrs);

        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

    }

    /**
     * get the styled attributes
     *
     * @param attrs
     */
    private void obtainStyledAttributes(Context context, AttributeSet attrs) {
        // init values from custom attributes
        TypedArray attributes = context.obtainStyledAttributes(
                attrs, R.styleable.RTLProgressBar);

        mTextColor = attributes.getColor(
                R.styleable.RTLProgressBar_progress_text_color,
                DEFAULT_TEXT_COLOR);
        mTextSize = attributes.getDimension(
                R.styleable.RTLProgressBar_progress_text_size,
                DEFAULT_TEXT_SIZE);

        mReachedBarColor = attributes
                .getColor(
                        R.styleable.RTLProgressBar_progress_reached_color,
                        DEFAULT_TEXT_COLOR);
        mBackgroudColor = attributes
                .getColor(
                        R.styleable.RTLProgressBar_progress_background_color,
                        DEFAULT_BACKGROUD_COLOR);
        mProgressHeight = attributes
                .getDimension(
                        R.styleable.RTLProgressBar_progress_height,
                        DEFAULT_HEIGHT);
        mTextOffset = (int) attributes
                .getDimension(
                        R.styleable.RTLProgressBar_progress_text_offset,
                        mTextOffset);

        attributes.recycle();
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (heightMode != MeasureSpec.EXACTLY) {

            float textHeight = (mPaint.descent() + mPaint.ascent());
            int exceptHeight = (int) (getPaddingTop() + getPaddingBottom() + Math
                    .max(mProgressHeight, Math.abs(textHeight)));

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(exceptHeight,
                    MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
        //画笔平移到指定paddingLeft， getHeight() / 2位置，注意以后坐标都为以此为0，0
        canvas.translate(getPaddingLeft(), getHeight() / 2);

        boolean noNeedBg = false;
        //当前进度和总值的比例
        float radio = getProgress() * 1.0f / getMax();
        //已到达的宽度
        float progressPosX = (int) (mRealWidth * radio);
        //绘制的文本
        String text = getProgress() + "%";


        //拿到字体的宽度和高度
        float textWidth = mPaint.measureText(text);
        float textHeight = (mPaint.descent() + mPaint.ascent()) / 2;

        //如果到达最后，则未到达的进度条不需要绘制
        if (progressPosX + textWidth > mRealWidth) {
            progressPosX = mRealWidth - textWidth;
            noNeedBg = true;
        }

        // 绘制已到达的进度
        float endX = progressPosX - mTextOffset / 2;
        if (endX > 0) {
            mPaint.setColor(mReachedBarColor);
            mPaint.setStrokeWidth(mProgressHeight);
            canvas.drawLine(0, 0, endX, 0, mPaint);
        }

        // 绘制文本
        mPaint.setColor(mTextColor);
        canvas.drawText(text, progressPosX, -textHeight, mPaint);

        // 绘制未到达的进度条
        if (!noNeedBg) {
            float start = progressPosX + mTextOffset / 2 + textWidth;
            mPaint.setColor(mBackgroudColor);
            mPaint.setStrokeWidth(mProgressHeight);
            canvas.drawLine(start, 0, mRealWidth, 0, mPaint);
        }

        canvas.restore();

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRealWidth = w - getPaddingRight() - getPaddingLeft();

    }

    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    @Deprecated
    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
    }

    public void toProgress(int progress) {
        progress=progress*100/getMax();
        setMax(100);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, progress);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int animatedValue = (int) animator.getAnimatedValue();
                setProgress(animatedValue);
            }
        });
        valueAnimator.setDuration(progress * 10);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();
    }

}
