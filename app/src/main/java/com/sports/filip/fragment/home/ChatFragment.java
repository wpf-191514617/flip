package com.sports.filip.fragment.home;

import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.sports.filip.R;
import com.sports.filip.fragment.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by pengfei on 2016/11/14.
 */

public class ChatFragment extends BaseFragment
{
    
    private Handler mHandler = new Handler();
    
    @Bind(R.id.layoutfootballchat)
    LinearLayout layoutfootballchat;
    @Bind(R.id.layoutBaseketballchat)
    LinearLayout layoutBaseketballchat;

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.fragment_chat;
    }

    @Override
    protected void initViewAndData()
    {

    }


    @OnClick({R.id.layoutfootballchat, R.id.layoutBaseketballchat})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.layoutfootballchat:
                chat();
                break;
            case R.id.layoutBaseketballchat:
                chat();
                break;
        }
    }
    
    private void chat(){
        mHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                showToastShort("初始化 IM SDK失败！");
            }
        } , 1500);
    }
    
}
