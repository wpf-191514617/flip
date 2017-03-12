package com.sports.filip.fragment.home;


import com.sports.filip.fragment.base.BaseFragment;
import com.sports.filip.fragment.base.BaseTabFragment;
import com.sports.filip.fragment.home.score.AttentionFragment;
import com.sports.filip.fragment.home.score.CompetitionFragment;
import com.sports.filip.fragment.home.score.ExponentialFragment;
import com.sports.filip.fragment.home.score.InstantFragment;
import com.sports.filip.fragment.home.score.RollBallFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengfei on 2016/11/13.
 */

public class ScoreFragment extends BaseTabFragment
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
