package com.sports.filip.agreement;

/**
 * Created by pengfei on 2016/6/28.
 */
public interface IAsyncComplete<T> {
    
    public abstract void onComplete(T result);
    
}
