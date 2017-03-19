package com.sports.filip;

import android.content.Context;

import com.awhh.everyenjoy.library.base.MyApplication;

import io.rong.imkit.RongIM;

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
        RongIM.init(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
       // MultiDex.install(this);
        
    }
}
