package com.sports.filip.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.sports.filip.R;
import com.sports.filip.activity.base.BaseActivity;

import io.rong.imkit.RongExtension;
import io.rong.imkit.fragment.ConversationFragment;

/**
 * author:pengfei
 * date:2017/3/19
 * Email:15291967179@163.com
 */

public class ConversationActivity extends BaseActivity
{
    @Override
    protected String getTitleString()
    {
        return null;
    }

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.activty_conversation;
    }

    @Override
    protected void getBundleExtras(Bundle extras)
    {

    }

    @Override
    protected void initViewAndData()
    {
        RongExtension rongExtension = new RongExtension(this);
        rongExtension.getInputEditText().setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onBackPressed() {
        ConversationFragment fragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);
        if(!fragment.onBackPressed()) {
            finish();
        }
    }
    
}
