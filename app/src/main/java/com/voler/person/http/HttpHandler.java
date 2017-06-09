package com.voler.person.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * HttpHandler Created by voler on 2017/6/5.
 * 说明：
 */

public class HttpHandler {
    Response onResponse(String httpResult, Interceptor.Chain chain, Response response) {
        return response;
    }

    okhttp3.Response onRequest(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .header("Server-Version", "1.0")
           .build();
        return chain.proceed(request);
    }
}
