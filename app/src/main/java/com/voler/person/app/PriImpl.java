package com.voler.person.app;

import android.util.Log;

/**
 * PriImpl Created by voler on 2017/5/19.
 * 说明：
 */

public class PriImpl implements PriInterface{
    @Override
    public void pri() {
        Log.e("-----","extend");
    }
}
