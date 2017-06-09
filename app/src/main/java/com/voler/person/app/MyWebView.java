package com.voler.person.app;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
        setWebViewClient(new WebViewClient());
        setWebChromeClient(new WebChromeClient());

        WebSettings webSettings = getSettings();
        //支持javascript
        webSettings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webSettings.setSupportZoom(true);
        //扩大比例的缩放
        webSettings.setUseWideViewPort(true);
        //自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
    }


    class JSObj {
        @JavascriptInterface
        public void login() {

        }

        public void push(){

        }

    }

}
