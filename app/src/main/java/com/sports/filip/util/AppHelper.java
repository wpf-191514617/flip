package com.sports.filip.util;

import com.sports.filip.Constants;

/**
 * author:pengfei
 * date:2017/4/9
 * Email:15291967179@163.com
 */

public class AppHelper
{
    public static String getInvatationUrl(){
        String url = Constants.BASEURL + "index.php?g=app&m=user&a=register_rec&userid="
                +CacheHelper.getUserId();
        return url;
    }
}
