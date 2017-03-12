package com.sports.filip.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.sports.filip.R;
import com.sports.filip.activity.base.BaseActivity;


/**
 * Created by pengfei on 2016/11/18.
 */

public class FeedBackActivity extends BaseActivity
{
    @Override
    protected String getTitleString() {
        return "意见反馈";
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewAndData() {
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.done){
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_commit , menu);
        return super.onCreateOptionsMenu(menu);
    }
}
