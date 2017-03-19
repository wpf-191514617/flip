package com.sports.filip.fragment.home;

import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.awhh.everyenjoy.library.base.util.StringUtils;
import com.sports.filip.R;
import com.sports.filip.activity.account.LoginActivity;
import com.sports.filip.fragment.base.BaseFragment;
import com.sports.filip.util.CacheHelper;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

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
    
    private final String GROUP_ID_SCORE = "1";
    private final String GROUP_ID_BASEKETBALL = "2";

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
                chat(GROUP_ID_SCORE);
                break;
            case R.id.layoutBaseketballchat:
                chat(GROUP_ID_BASEKETBALL);
                break;
        }
    }
    
    private void chat(String groupId){
        if(StringUtils.isEmpty(CacheHelper.getUserId())){
            readyGo(LoginActivity.class);
            return;
        }

        RongIM.getInstance().startGroupChat(getActivity() , groupId , "群聊");
        
    }
    
}
