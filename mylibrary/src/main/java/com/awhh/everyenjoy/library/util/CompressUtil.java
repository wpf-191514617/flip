/*package com.awhh.everyenjoy.library.util;

import com.van.fanyu.library.Compresser;

import java.util.ArrayList;
import java.util.List;

*//**
 * Created by pengfei on 2016/6/17.
 *//*
public class CompressUtil {
    
    public static List<String> compressList(List<String> filePath){
        final List<String> newPaths = new ArrayList<>();
        
        for (int i = 0;i < filePath.size();i++)
        {
            Compresser compresser = new Compresser(50 , filePath.get(i));

            compresser.doCompress(new Compresser.CompleteListener() {
                @Override
                public void onSuccess(String s) {
                    newPaths.add(s);
                }
            });
        }
        return newPaths;
    }
    
    public static String compressOnly(String src){

        final String[] filePath = new String[1];
        
        Compresser compresser = new Compresser(30 , src);
        compresser.doCompress(new Compresser.CompleteListener() {
            @Override
            public void onSuccess(String s) {
                filePath[0] = s;
            }
        });
        return filePath[0];
    }
    
    public static void compressOnly(String src , OnCompressListener listener){

        Compresser compresser = new Compresser(30 , src);
        compresser.doCompress(new Compresser.CompleteListener() {
            @Override
            public void onSuccess(String s) {
                
            }
        });
        
    }
    
    
}*/
