package com.sports.filip.util;


import com.awhh.everyenjoy.library.util.CountDownTimer;
import com.sports.filip.util.callback.CountTimerCallBack;

/**
 * Created by pengfei on 2016/11/13.
 */

public class MyCountDownTimer extends CountDownTimer
{
    
    private CountTimerCallBack timerCallBack;
    
    public MyCountDownTimer(long millisInFuture, long countDownInterval, CountTimerCallBack timerCallBack) {
        super(millisInFuture, countDownInterval);
        this.timerCallBack = timerCallBack;
    }

    @Override
    public void onTick(long l) {
        if(timerCallBack != null)
        {
            timerCallBack.onTick(l);
        }
    }

    @Override
    public void onFinish() {
        if(timerCallBack!=null)
            timerCallBack.onFinish();
    }
}
