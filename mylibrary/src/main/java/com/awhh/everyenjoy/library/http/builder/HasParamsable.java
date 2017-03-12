package com.awhh.everyenjoy.library.http.builder;

import java.util.Map;

/**
 * Created by zhy on 16/3/1.
 */
public interface HasParamsable
{
    com.awhh.everyenjoy.library.http.builder.OkHttpRequestBuilder params(Map<String, String> params);
    com.awhh.everyenjoy.library.http.builder.OkHttpRequestBuilder addParams(String key, String val);
}
