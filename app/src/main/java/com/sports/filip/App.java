package com.sports.filip;

import com.awhh.everyenjoy.library.base.MyApplication;
import com.awhh.everyenjoy.library.http.OkHttpUtils;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.concurrent.TimeUnit;

import io.rong.imkit.RongIM;
import okhttp3.OkHttpClient;

/**
 * author:pengfei
 * date:2017/1/8
 * Email:15291967179@163.com
 */

public class App extends MyApplication
{

    @Override
    public void onCreate()
    {
        super.onCreate();
        UMShareAPI.get(this);
        RongIM.init(getApplicationContext());
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(20000L, TimeUnit.MILLISECONDS)
                .readTimeout(20000L, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.getInstance().initClient(httpClient);
    }

    {
        PlatformConfig.setQQZone("1106084332", "LVEYMv4LWtoCg9Ew");
    }
}
