package com.voler.person.app.video;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.voler.person.app.R;
import com.voler.person.app.shortcut.ShortUtil;

/**
 * VideoActivity Created by voler on 2017/5/25.
 * 说明：
 */

public class VideoActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setMediaController(new MediaController(this));
//        videoView.setVideoURI(Uri.parse("http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4"));
        videoView.setVideoURI(Uri.parse("http://src.mysada.com/test/test.mp4"));
        videoView.start();
        ShortUtil.addShortcut(this);
    }
}
