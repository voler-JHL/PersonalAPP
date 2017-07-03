package com.voler.person.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.voler.annotation.FieldInject;
import com.voler.saber.Saber;
import com.voler.person.app.jsbridge.MyWebView;


/**
 * WebActivity Created by voler on 2017/6/8.
 * 说明：
 */

public class WebActivity extends Activity{
    @FieldInject
    String stringname;
    @FieldInject
    int stringNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        MyWebView webview = (MyWebView) findViewById(R.id.webview);
//        ReportUtil.nishishui("","fjdk");

        Saber.inject(this);
        Toast.makeText(this,stringname+stringNum,Toast.LENGTH_LONG).show();
    }
}
