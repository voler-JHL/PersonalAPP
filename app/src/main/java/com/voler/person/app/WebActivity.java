package com.voler.person.app;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.UserDictionary;
import android.util.Log;
import android.widget.Toast;

import com.voler.annotation.FieldInject;
import com.voler.person.app.jsbridge.MyWebView;
import com.voler.saber.Saber;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * WebActivity Created by voler on 2017/6/8.
 * 说明：
 */

public class WebActivity extends Activity {
    @FieldInject
    String stringname;
    @FieldInject
   public int
            [] hfdakl;
    @FieldInject
   protected String              [] fda;
     @FieldInject
   protected ArrayList<String> fdda;
    @FieldInject
    int stringNum;
    @FieldInject
    HHHH vczxv;
    private String tag;
   static class HHHH implements Parcelable{

        protected HHHH(Parcel in) {
        }

        public static final Creator<HHHH> CREATOR = new Creator<HHHH>() {
            @Override
            public HHHH createFromParcel(Parcel in) {
                return new HHHH(in);
            }

            @Override
            public HHHH[] newArray(int size) {
                return new HHHH[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        MyWebView webview = (MyWebView) findViewById(R.id.webview);
//        ReportUtil.nishishui("","fjdk");

        Saber.inject(this);
        com.voler.saber.FragmentFactory.createInjectFragment("nihao",null);

//        Toast.makeText(this,stringname+stringNum,Toast.LENGTH_LONG).show();
        tag = getClass().getSimpleName();
        Log.e(tag, history());
//        new GetInternetRecord().getRecords(getContentResolver());
//        Toast.makeText(this, history(), Toast.LENGTH_LONG).show();

        insert();
        getWord();

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("haha");
            }
        }).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return "666";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(WebActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void insert() {
        // 定义一个新的Uri对象，用于接收插入后的返回值
        Uri mNewUri;


// 定义一个对象来包含要插入的值们
        ContentValues mNewValues = new ContentValues();

/*
 * 设置要插入行的每列的值．"put"方法的参数是"column name"和"value"
 */
        mNewValues.put(UserDictionary.Words.APP_ID, "example.user");
        mNewValues.put(UserDictionary.Words.LOCALE, "en_US");
        mNewValues.put(UserDictionary.Words.WORD, "insert");
        mNewValues.put(UserDictionary.Words.FREQUENCY, "100");

        mNewUri = getContentResolver().insert(
                UserDictionary.Words.CONTENT_URI,   // 用户词典的content URI
                mNewValues                          // 要插入的值们
        );

    }

    private void getWord() {
        Cursor cursor = getContentResolver().query(
                UserDictionary.Words.CONTENT_URI,   // the user dictionary content URI
                new String[]{UserDictionary.Words.APP_ID,
                        UserDictionary.Words.LOCALE,
                        UserDictionary.Words.WORD,
                        UserDictionary.Words.FREQUENCY}, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            String url = null;
            String title = null;
            String time = null;
            String date = null;

            title = cursor.getString(cursor.getColumnIndex("appid"));
            url = cursor.getString(cursor.getColumnIndex("locale"));

            date = cursor.getString(cursor.getColumnIndex("word"));

//            SimpleDateFormat dateFormat = new SimpleDateFormat(
//                    "yyyy-MM-dd hh:mm;ss");
//            Date d = new Date(Long.parseLong(date));
//            time = dateFormat.format(d);

            Log.e(getClass().getSimpleName(), title + url + date);
            Toast.makeText(WebActivity.this, title + url + date, Toast.LENGTH_LONG).show();
        }
    }

    private String history() {
        String string = "no history";
        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(
                Uri.parse("content://browser/bookmarks"),
                new String[]{"url"}, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            string = cursor.getString(cursor.getColumnIndex("url"));
            Log.d("debug", string == null ? "null" : string);
        }
        return string;
    }

    public class GetInternetRecord {
        String records = null;
        StringBuilder recordBuilder = null;

        public void getRecords(ContentResolver contentResolver) {
            // ContentResolver contentResolver = getContentResolver();
            Cursor cursor = contentResolver.query(
                    Uri.parse("content://browser/bookmarks"), new String[]{
                            "title", "url", "date"}, "date!=?",
                    new String[]{"null"}, "date desc");
            if (cursor == null) {
                Log.e(tag, "null");
            } else {
                Log.e(tag, "not null");
            }
            while (cursor != null && cursor.moveToNext()) {
                String url = null;
                String title = null;
                String time = null;
                String date = null;

                recordBuilder = new StringBuilder();
                title = cursor.getString(cursor.getColumnIndex("title"));
                url = cursor.getString(cursor.getColumnIndex("url"));

                date = cursor.getString(cursor.getColumnIndex("date"));

                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "yyyy-MM-dd hh:mm;ss");
                Date d = new Date(Long.parseLong(date));
                time = dateFormat.format(d);

                Log.e(getClass().getSimpleName(), title + url + time);
                Toast.makeText(WebActivity.this, title + url + time, Toast.LENGTH_LONG).show();
            }
        }
    }
//        private JSONObject getBrowserBookmarks() {
//        JSONArray data = new JSONArray();
//        // data.add("哈哈哈哈");
//        ContentResolver contentResolver = getContentResolver();
//        Cursor cursor = contentResolver.query(Browser.BOOKMARKS_URI, null,
//                null, null, null);
//
//        while (cursor != null && cursor.moveToNext()) {
//            String bookTitle = cursor.getString(cursor
//                    .getColumnIndex(Browser.BookmarkColumns.TITLE));Browser.EXTRA_CREATE_NEW_TAB
//            if (cursor.getString(
//                    cursor.getColumnIndex(Browser.BookmarkColumns.BOOKMARK))
//                    .equals("1") && !TextUtils.isEmpty(bookTitle)) {
//                String url = cursor.getString(cursor
//                        .getColumnIndex(Browser.BookmarkColumns.URL));
//                JSONObject obj = new JSONObject();
//            }
//        }
//        if (cursor != null)
//            cursor.close();
//        JSONObject jsonObject = new JSONObject();
//        return jsonObject;
//
//    }

}
