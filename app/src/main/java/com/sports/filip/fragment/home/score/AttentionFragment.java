package com.sports.filip.fragment.home.score;

/**
 * Created by pengfei on 2016/11/17.
 * 
 *  足球------关注
 * 
 */

public class AttentionFragment extends ExponentialFragment{

    @Override
    protected void getData()
    {
        mHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                showNormal();
            }
        } , 3000);
    }
}
