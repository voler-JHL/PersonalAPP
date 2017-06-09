package com.voler.person.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * WebActivity Created by voler on 2017/6/8.
 * 说明：
 */

public class WebActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        WebView webview = (WebView) findViewById(R.id.webview);
        webview.loadUrl("file:///android_asset/doc_template/index.html");
    }
}
