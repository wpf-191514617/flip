package com.sports.filip;

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
//        UMShareAPI.get(this);
    }

//    {
//        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//    }
}
