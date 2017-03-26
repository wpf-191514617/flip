package com.sports.filip.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.awhh.everyenjoy.library.base.util.BaseAppManager;
import com.awhh.everyenjoy.library.base.util.StringUtils;
import com.awhh.everyenjoy.library.http.utils.GsonUtils;
import com.awhh.everyenjoy.library.util.ACache;
import com.sports.filip.App;
import com.sports.filip.Constants;
import com.sports.filip.entity.LoginUser;
import com.sports.filip.entity.response.UserResponse;

/**
 * author:pengfei
 * date:2017/1/16
 * Email:15291967179@163.com
 */

public class CacheHelper
{
    private static String userId;

    public static void saveCurrentUser(UserResponse response)
    {
        saveCurrentUser(GsonUtils.gsonString(response));
    }

    public static void saveCurrentUser(String userStr)
    {
        Context context = BaseAppManager.getInstance().getTopActivity();
        ACache aCache = ACache.get(context, context.getPackageName());
        aCache.put(Constants.CacheKey.USER, userStr, ACache.TIME_DAY * 365 * 10);
    }

    
    public static String getUserId(){
       if (getUser() == null)
       {
           return null;
       }
        return getUser().getId();
    }
    
    private static String getUserIdFromCache()
    {
        
        UserResponse userResponse = getUser();
        if (null == userResponse)
            return null;
        userId = userResponse.getId();
        return userId;
    }
    
    public static UserResponse getUser(){
        Context context = BaseAppManager.getInstance().getTopActivity();
        ACache aCache = ACache.get(context, context.getPackageName());
        String user = aCache.getAsString(Constants.CacheKey.USER);
        if (StringUtils.isEmpty(user))
            return null;
        return GsonUtils.gsonToBean(user , UserResponse.class);
    }
    
    
    public static void saveCurrentLoginUserInfo(String name , String pass){
        SharedPreferences preferences = App.getAppContext().getSharedPreferences("flip" , Activity.MODE_PRIVATE);
        preferences.edit().putString("name" , name).putString("pass" , pass).commit();
    }
    
    public static LoginUser getCurrentLoginUserInfo(){
        SharedPreferences preferences = App.getAppContext().getSharedPreferences("flip" , Activity.MODE_PRIVATE);
        String name = preferences.getString("name" , "");
        String pass = preferences.getString("pass" , "");
        return new LoginUser(name , pass);
    }
    
}
