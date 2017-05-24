package com.voler.person.app;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

/**
 * MyWebView Created by voler on 2017/5/24.
 * 说明：
 */

public class MyWebView extends WebView {

    public MyWebView(Context context) {
        this(context, null);
    }


    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        addJavascriptInterface(new JSObj(), "TMD");
    }


    class JSObj {
        @JavascriptInterface
        public void login() {

        }

    }

}
