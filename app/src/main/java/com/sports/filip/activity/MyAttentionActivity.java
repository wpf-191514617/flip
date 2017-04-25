package com.sports.filip.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.sports.filip.R;
import com.sports.filip.activity.base.BaseActivity;
import com.sports.filip.adapter.BaseTabFragmentAdapter;
import com.sports.filip.fragment.base.BaseFragment;
import com.sports.filip.fragment.home.baseketball.AttentionFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author:pengfei
 * date:2017/4/5
 * Email:15291967179@163.com
 */

public class MyAttentionActivity extends BaseActivity
{
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected String getTitleString()
    {
        return "我的关注";
    }

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.activity_tablayout;
    }

    @Override
    protected void getBundleExtras(Bundle extras)
    {

    }

    @Override
    protected void initViewAndData()
    {
        List<String> title = new ArrayList<>();
        title.add("足球");
        title.add("篮球");
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new AttentionFragment());
        fragments.add(new AttentionFragment());
        BaseTabFragmentAdapter adapter = new BaseTabFragmentAdapter(getSupportFragmentManager() , fragments);
        adapter.setTitles(title);
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
    }
}
