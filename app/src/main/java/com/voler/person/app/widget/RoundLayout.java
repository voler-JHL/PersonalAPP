package com.voler.person.app.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

/**
 * MyRel Created by voler on 2017/7/31.
 * 说明：
 */

public class RoundLayout extends RelativeLayout {
    private float roundLayoutRadius = 14f;
    private int l;
    private int t;

    private int x = 500;
    private int y = 300;
    private int width;
    private int height;
    private int shortSide = 5;
    private int longSide = 40;
    private int arrowHeight = 12;

    public RoundLayout(Context context) {
        this(context, null);
    }

    public RoundLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        width = dm.widthPixels;
        height = dm.heightPixels;

        shortSide = (int) (shortSide * density);
        longSide = (int) (longSide * density);
        arrowHeight = (int) (arrowHeight * density);

//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundLayout);
//        roundLayoutRadius = typedArray.getDimensionPixelSize(R.styleable.RoundLayout_roundLayoutRadius, (int) roundLayoutRadius);
//        typedArray.recycle();

        init();
    }

    private void init() {
        setWillNotDraw(false);//如果你继承的是ViewGroup,注意此行,否则draw方法是不会回调的;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        this.l = l;
        this.t = t;
    }

    @Override
    public void draw(Canvas canvas) {
        Path path = new Path();
        if (y > width / 2) {
            path.moveTo(x - longSide, t);
            path.quadTo(x - shortSide, t, x, t - arrowHeight);
            path.quadTo(x, t, x + longSide, t);
        } else {
            path.moveTo(x - shortSide, t);
            path.quadTo(x + shortSide, t, x, t - arrowHeight);
            path.quadTo(x + shortSide, t, x + longSide, t);
        }


        Paint paint = new Paint();
        paint.setColor(0xffff0000);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);

        super.draw(canvas);
    }

    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
