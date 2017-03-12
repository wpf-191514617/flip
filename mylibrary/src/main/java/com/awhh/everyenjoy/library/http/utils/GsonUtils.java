package com.awhh.everyenjoy.library.http.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;


public class GsonUtils
{

    private static Gson mGson = null;
    
    public static Gson getGson(){
        return mGson;
    }

    static
    {
        if (mGson == null)
        {
            mGson = new Gson();
        }
    }

    private GsonUtils()
    {
    }

    public static String gsonString(Object object)
    {
        String gsonStr = null;
        if (mGson != null)
        {
            gsonStr = mGson.toJson(object);
        }
        return gsonStr;
    }

    public static <T> T gsonToBean(String jsonStr, Class<T> clz)
    {
        T t = null;
        if (mGson != null)
        {
            t = mGson.fromJson(jsonStr, clz);
        }
        return t;
    }

    /**
     * 转成list
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> gsonToList(String gsonString, Class<T> cls) {
        List<T> list = null;
        if (mGson != null) {
            list = mGson.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (mGson != null) {
            list = mGson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>()
                    {
                    }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (mGson != null) {
            map = mGson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }




}
