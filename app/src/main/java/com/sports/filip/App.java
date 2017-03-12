package com.sports.filip;

import android.content.Context;

import com.awhh.everyenjoy.library.base.MyApplication;

/**
 * author:pengfei
 * date:2017/1/8
 * Email:15291967179@163.com
 */

public class App extends MyApplication
{
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
       // MultiDex.install(this);
    }
}
