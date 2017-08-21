package com.voler.person.app.lock;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.DateUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.voler.person.app.R;

import java.util.ArrayList;

/**
 * MyLockScreenActivity Created by voler on 2017/6/9.
 * 说明：
 */

public class MyLockScreenActivity extends Activity {

    private TextView tvData;
    private ViewPager vpNews;
    private ArrayList<View> views;
    private ImageView ivFinish;
    private float initX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_lock);
        tvData = (TextView) findViewById(R.id.tv_data);
        vpNews = (ViewPager) findViewById(R.id.vp_news);
        ivFinish = (ImageView) findViewById(R.id.iv_finish);
        ivFinish.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initX = event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
//                        moveX = event.getX() - initX;
//                        requestLayout();
//                        invalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (initX - event.getX() > 100) {
                            MyLockScreenActivity.this.finish();
                        }
                        break;
                }
                return true;
            }
        });


        views = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundColor(0xffffffff);
            views.add(imageView);
        }
//        vpNews.setPageMargin(80);
        vpNews.setOffscreenPageLimit(3);
        vpNews.setAdapter(new MyAdapter(this));
        vpNews.setCurrentItem(views.size() - 1);


    }

    public String formatDateStampString() {
        long when = System.currentTimeMillis();
        int format_flags = DateUtils.FORMAT_NO_NOON_MIDNIGHT
                | DateUtils.FORMAT_ABBREV_ALL
                | DateUtils.FORMAT_SHOW_DATE
                | DateUtils.FORMAT_SHOW_WEEKDAY;
        return DateUtils.formatDateTime(this, when, format_flags);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvData.setText(formatDateStampString());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float initX = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float v = event.getX() - initX;
                break;
            case MotionEvent.ACTION_UP:
                initX = event.getX();
                break;
        }
        return super.onTouchEvent(event);
    }

    //PagerAdapter是object的子类
    class MyAdapter extends PagerAdapter {


        private final Context mContext;

        public MyAdapter(Context context) {
            mContext = context;
        }

        /**
         * PagerAdapter管理数据大小
         */
        @Override
        public int getCount() {
            return views.size();
        }

        /**
         * 关联key 与 obj是否相等，即是否为同一个对象
         */
        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj; // key
        }

        /**
         * 销毁当前page的相隔2个及2个以上的item时调用
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object); // 将view 类型 的object熊容器中移除,根据key
        }

        /**
         * 当前的page的前一页和后一页也会被调用，如果还没有调用或者已经调用了destroyItem
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view;
            if (position == views.size() - 1) {
                view = View.inflate(mContext, R.layout.item_lock_first, null);
                view.findViewById(R.id.tv_open_app).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        open app
                    }
                });
            } else {
                view = View.inflate(mContext, R.layout.item_lock_normal, null);
            }
            container.addView(view);
            return view; // 返回该view对象，作为key
        }
    }
}
