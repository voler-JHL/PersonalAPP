package com.voler.person.app.lock;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.WindowManager;
import android.widget.TextView;

import com.voler.person.app.R;

/**
 * MyLockScreenActivity Created by voler on 2017/6/9.
 * 说明：
 */

public class MyLockScreenActivity extends Activity {

    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        setContentView(R.layout.activity_lock);
        tvData = (TextView) findViewById(R.id.tv_data);


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
}
