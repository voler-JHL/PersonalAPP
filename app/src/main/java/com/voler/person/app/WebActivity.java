package com.voler.person.app;

import android.app.Activity;
import android.os.Bundle;

import com.onemena.util.ReportUtil;
import com.voler.person.app.jsbridge.MyWebView;


/**
 * WebActivity Created by voler on 2017/6/8.
 * 说明：
 */

public class WebActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        MyWebView webview = (MyWebView) findViewById(R.id.webview);
        ReportUtil.nishishui("","fjdk");
    }
}
