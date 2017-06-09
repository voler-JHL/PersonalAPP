package com.voler.person.app.lock;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.voler.person.app.R;

/**
 * MyLockScreenActivity Created by voler on 2017/6/9.
 * 说明：
 */

public class MyLockScreenActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        setContentView(R.layout.activity_lock);
    }
}
