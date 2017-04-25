package com.sports.filip.fragment.home.baseketball;

import com.sports.filip.util.ScoreUtil;

import java.util.List;

/**
 * Created by pengfei on 2016/11/17.
 * 
 *  篮球-----滚球
 * 
 */

public class RollBallFragment extends CompetitionFragment {

    @Override
    protected List<String> getDays() {
        return ScoreUtil.getPassedSevenDays();
    }

    @Override
    protected String getType() {
        return "2";
    }
}
