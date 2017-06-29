package com.voler.person.http;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * CommonService Created by voler on 2017/6/5.
 * 说明：
 */

public interface CommonService {

    @GET("http://help.mysada.com/api/ad_start/get/wasel")
    Observable<ResponseBody> getSplashAdv();

    @GET("http://47.91.65.163:17316/arabic/search.do?appid=1")
    Observable<JsonObject> search(@Query("kword") String kword);

    @GET("ar_AE/api/article_status/{article_id}")
    Observable<JsonObject> getstatus(@Path("article_id") String article_id);
}
