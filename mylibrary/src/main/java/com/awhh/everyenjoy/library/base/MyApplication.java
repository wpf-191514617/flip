package com.awhh.everyenjoy.library.base;

import android.app.Application;
import android.content.Context;

import com.awhh.everyenjoy.autolayout.config.AutoLayoutConifg;


/**
 * Created by admin on 2016/5/3.
 */
public class MyApplication extends Application {

    private static Context appContext;
    
    public static Context getAppContext(){
        return appContext;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        AutoLayoutConifg.getInstance().useDeviceSize();
//        initImageLoader();
    }

    public static void initImageLoader() {
       /* ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(appContext);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs();
        ImageLoader.getInstance().init(config.build());*/
    }
}
