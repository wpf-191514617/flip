package com.sports.filip.util;

import com.awhh.everyenjoy.library.util.ACache;
import com.sports.filip.App;

/**
 * Author：huafang2016
 * Date: 2016/8/23 13:08
 * Email：15291967179@163.com
 */
public class AcacheUtil
{

    
    public static void putSmdseq(String arg0){
        ACache aCache = ACache.get(App.getAppContext(), "smdseq_cache");
        aCache.put("smdseq", arg0, ACache.TIME_DAY * 365 * 10);
    }
    
   
    
}