package com.sports.filip.agreement.callback;

/**
 * Created by pengfei on 2016/6/29.
 */
public interface OnPayCallBack {
    
    
    void onPay(String charge);
    
    void onFailed();
    
}
