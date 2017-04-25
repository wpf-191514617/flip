package com.sports.filip;

import com.sports.filip.util.CacheHelper;

/**
 * Created by pengfei on 2016/11/13.
 */

public class Constants {
    
    public static class CountTimer{
        public static final long millisInFuture = 60 * 1000;
        public static final long countDownInterval = 1 * 1000;
    }
    
    public static class CacheKey{
        public static final String USER = "user";
    }
    
    public static class DBName{
        public static final String FOLLOW_SCORE = "follow_score_" + CacheHelper.getUserId();
        public static final String FOLLOW_BASKETBALL = "follow_basket_" + CacheHelper.getUserId();
    }
    
   // public static final String BASEURL = "http://59.188.133.108/";
    public static final String BASEURL = "http://football.eyunshop.cn/";
    public static final String BaseUrl = BASEURL;
    //public static final String BaseUrl = "http://114.55.227.5/";
    
   // public static final String URL = "http://football.eyunshop.cn/";
    
}
