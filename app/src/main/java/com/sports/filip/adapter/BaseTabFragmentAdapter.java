package com.sports.filip.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sports.filip.fragment.base.BaseFragment;

import java.util.List;

/**
 * author:pengfei
 * date:2017/4/5
 * Email:15291967179@163.com
 */

public class BaseTabFragmentAdapter extends FragmentStatePagerAdapter
{
    
    private List<BaseFragment> fragments;
    
    private List<String> titles;

    public void setTitles(List<String> titles)
    {
        this.titles = titles;
    }

    public List<BaseFragment> getFragments()
    {
        return fragments;
    }

    public void setFragments(List<BaseFragment> fragments)
    {
        this.fragments = fragments;
    }

    public BaseTabFragmentAdapter(FragmentManager fm , List<BaseFragment> fragments)
    {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments == null ? null : fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titles != null ? titles.get(position) : "";
    }
}
