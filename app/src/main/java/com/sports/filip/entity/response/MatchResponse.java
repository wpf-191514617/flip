package com.sports.filip.entity.response;

/**
 * author:pengfei
 * date:2017/4/14
 * Email:15291967179@163.com
 */

public class MatchResponse
{


    /**
     * match_info_id : 23
     * current_round : 35
     * match_time : 2017/04/13 20:30
     * h_name : 奥尔比亚
     * a_name : 凯勒雷斯
     * match_id : 3
     * score : 
     */

    private String match_info_id;
    private String current_round;
    private String match_time;
    private String h_name;
    private String a_name;
    private String match_id;
    private String score;

    public String getMatch_info_id()
    {
        return match_info_id;
    }

    public void setMatch_info_id(String match_info_id)
    {
        this.match_info_id = match_info_id;
    }

    public String getCurrent_round()
    {
        return current_round;
    }

    public void setCurrent_round(String current_round)
    {
        this.current_round = current_round;
    }

    public String getMatch_time()
    {
        return match_time;
    }

    public void setMatch_time(String match_time)
    {
        this.match_time = match_time;
    }

    public String getH_name()
    {
        return h_name;
    }

    public void setH_name(String h_name)
    {
        this.h_name = h_name;
    }

    public String getA_name()
    {
        return a_name;
    }

    public void setA_name(String a_name)
    {
        this.a_name = a_name;
    }

    public String getMatch_id()
    {
        return match_id;
    }

    public void setMatch_id(String match_id)
    {
        this.match_id = match_id;
    }

    public String getScore()
    {
        return score;
    }

    public void setScore(String score)
    {
        this.score = score;
    }
}
