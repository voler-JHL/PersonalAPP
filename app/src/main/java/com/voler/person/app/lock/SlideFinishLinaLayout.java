package com.voler.person.app.lock;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * SlideFinishLinaLayout Created by voler on 2017/7/18.
 * 说明：
 */

public class SlideFinishLinaLayout extends LinearLayout {

    private float moveX;

    public SlideFinishLinaLayout(Context context) {
        super(context);
    }

    public SlideFinishLinaLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideFinishLinaLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float initX = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = event.getX() - initX;
                requestLayout();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                initX = event.getX();
                break;
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        l = (int) (l - moveX);
        r = (int) (r - moveX);
        Log.i("_______", String.valueOf(moveX));
        Log.i("_______", String.valueOf(changed));


        super.onLayout(true, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
