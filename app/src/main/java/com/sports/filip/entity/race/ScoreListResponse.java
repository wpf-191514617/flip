package com.sports.filip.entity.race;

import com.sports.filip.entity.BaseRace;

import java.util.List;

/**
 * author:pengfei
 * date:2017/3/12
 * Email:15291967179@163.com
 */

public class ScoreListResponse extends BaseRace
{
    
    private List<ScoreEntity> list;

    public List<ScoreEntity> getList()
    {
        return list;
    }

    public void setList(List<ScoreEntity> list)
    {
        this.list = list;
    }
}
