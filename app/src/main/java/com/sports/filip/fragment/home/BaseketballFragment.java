package com.sports.filip.fragment.home;

import com.sports.filip.fragment.base.BaseFragment;
import com.sports.filip.fragment.base.BaseTabFragment;
import com.sports.filip.fragment.home.baseketball.AttentionFragment;
import com.sports.filip.fragment.home.baseketball.CompetitionFragment;
import com.sports.filip.fragment.home.baseketball.ExponentialFragment;
import com.sports.filip.fragment.home.baseketball.InstantFragment;
import com.sports.filip.fragment.home.baseketball.RollBallFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengfei on 2016/11/13.
 */

public class BaseketballFragment extends BaseTabFragment
{
    @Override
    protected String[] getTabNames() {
        return new String[]{"即时" , "滚球" , "赛事" ,"指数" , "关注"};
    }

    @Override
    protected List<BaseFragment> getFragments() {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new InstantFragment());
        fragments.add(new RollBallFragment());
        fragments.add(new CompetitionFragment());
        fragments.add(new ExponentialFragment());
        fragments.add(new AttentionFragment());
        return fragments;
    }
}
