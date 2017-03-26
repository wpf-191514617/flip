package com.sports.filip.fragment.home.score;

import com.awhh.everyenjoy.library.base.eventbus.EventCenter;
import com.awhh.everyenjoy.library.db.NiceDB;
import com.sports.filip.Constants;
import com.sports.filip.EventCode;
import com.sports.filip.adapter.ScoreListAdapter;
import com.sports.filip.entity.race.ScoreEntity;

import java.util.List;

/**
 * Created by pengfei on 2016/11/17.
 * 
 *  足球------关注
 * 
 */

public class AttentionFragment extends ExponentialFragment{

    private NiceDB niceDB;
    
    private List<ScoreEntity> scoreEntities;
    
    private ScoreListAdapter listAdapter;
    
    @Override
    protected void initViewAndData()
    {
        
        niceDB = NiceDB.create(getActivity() , Constants.DBName.FOLLOW_SCORE);
        scoreEntities = niceDB.findAll(ScoreEntity.class);
        refreshLayout.setIsRefresh(false);
        listAdapter = new ScoreListAdapter(getActivity() , null);
        listAdapter.setDelData(true);
        listView.setAdapter(listAdapter);
        getData();
    }

    @Override
    protected void getData()
    {
        niceDB = NiceDB.create(getActivity() , Constants.DBName.FOLLOW_SCORE);
        scoreEntities = niceDB.findAll(ScoreEntity.class);
        listAdapter.clearAddData(scoreEntities);
    }

    @Override
    protected boolean isRegisterEventBus()
    {
        return true;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter)
    {
        super.onEventComming(eventCenter);
        if (eventCenter.getEventCode() == EventCode.CODE_SCORE_FOLLOW 
                || eventCenter.getEventCode() == EventCode.CODE_LOGINSUCCESS)
            getData();
    }
}
