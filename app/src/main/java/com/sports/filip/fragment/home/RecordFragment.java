package com.sports.filip.fragment.home;

import com.sports.filip.fragment.base.BaseFragment;
import com.sports.filip.fragment.base.BaseTabFragment;
import com.sports.filip.fragment.home.record.AmericaMatchDataFragment;
import com.sports.filip.fragment.home.record.AsianMatchDataFragment;
import com.sports.filip.fragment.home.record.EuropeMatchDataFragment;
import com.sports.filip.fragment.home.record.OceaniaMatchDataFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengfei on 2016/11/14.
 */

public class RecordFragment extends BaseTabFragment
{

    @Override
    protected String[] getTabNames()
    {
        return new String[]{"欧洲赛事" , "美洲赛事" , "亚洲赛事" , "大洋洲赛事"};
    }
    
    @Override
    protected List<BaseFragment> getFragments()
    {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new EuropeMatchDataFragment());
        fragments.add(new AmericaMatchDataFragment());
        fragments.add(new AsianMatchDataFragment());
        fragments.add(new OceaniaMatchDataFragment());
        return fragments;
    }
}
