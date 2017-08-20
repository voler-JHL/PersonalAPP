package com.voler.person.app.web;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.voler.person.app.BuildConfig;

import java.util.Map;

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
        addJavascriptInterface(new JSObj(), "omApp");
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
        String userAgentString = webSettings.getUserAgentString();
        webSettings.setUserAgentString(userAgentString + " Onemena/" + BuildConfig.VERSION_NAME);
//        loadUrl("http://3.dev.arabsada.com");
//        loadUrl("javascript:omApp.login=function(callback){window.loginCallback=callback; omApp.login1(5);}");
//        loadUrl("javascript:omApp.login(function(success){omApp.login1(success)})");
        loadUrl("javascript:function login(s, a）{})");
        loadUrl("javascript:omApp.login(ssss,function(){})");
    }


    class JSObj {
        @JavascriptInterface
        public void login(String string,Callback listener) {
            Log.i("login","login");
//            o.onReceiveValue(false);
            Log.i("login", String.valueOf(string));
//            for (Method method : o.getClass().getMethods()) {
//                Log.i("login", method.getName());
//            }
            listener.loginScess();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    loadUrl("javascript:window.loginCallback(1)");
                }
            });
//            loadUrl("javascript:window.loginCallback(1)");
        }

        @JavascriptInterface
        public void http(Map<String,Object> map,ValueCallback<Object[]> o) {
            Log.i("http","http");
            o.onReceiveValue(new Object[]{false,null});
        }


    }

    class Callback{
        public void loginScess(){
            loadUrl("javascript:omApp.login(ssss,function(){})");
        }
    }
}
