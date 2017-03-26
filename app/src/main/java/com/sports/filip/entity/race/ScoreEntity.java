package com.sports.filip.entity.race;

import com.awhh.everyenjoy.library.db.annotate.Id;

/**
 * author:pengfei
 * date:2017/3/12
 * Email:15291967179@163.com
 */

public class ScoreEntity
{


    /**
     * id : 1252251
     * league : 荷甲
     * color : #FF6699
     * date : 03-12
     * time : 21:30
     * h_half : 2
     * a_half : 0
     * datetime : 83
     * h_yellow : 2
     * h_red : 0
     * h_name : 费耶诺德
     * h_score : 5
     * a_score : 1
     * a_name : 艾克马亚
     * a_red : 0
     * a_yellow : 0
     * d1 : 0.85
     * d2 : 一球
     * d3 : 1.05
     * d4 : 1.5
     * d5 : 4.2
     * d6 : 6.5
     * comments : 0
     * jingcai : 周日020
     * current : 3
     * h_rank : 1
     * a_rank : 6
     * a : 1489329266
     * is_follow : 0
     */
    @Id
    private int _id;
    
    private String id;
    private String league;
    private String color;
    private String date;
    private String time;
    private String h_half;
    private String a_half;
    private String datetime;
    private String h_yellow;
    private String h_red;
    private String h_name;
    private String h_score;
    private String a_score;
    private String a_name;
    private String a_red;
    private String a_yellow;
    private String d1;
    private String d2;
    private String d3;
    private String d4;
    private String d5;
    private String d6;
    private String comments;
    private String jingcai;
    private String current;
    private String h_rank;
    private String a_rank;
    private int a;
    private String is_follow;

    public int get_id()
    {
        return _id;
    }

    public void set_id(int _id)
    {
        this._id = _id;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getLeague()
    {
        return league;
    }

    public void setLeague(String league)
    {
        this.league = league;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getH_half()
    {
        return h_half;
    }

    public void setH_half(String h_half)
    {
        this.h_half = h_half;
    }

    public String getA_half()
    {
        return a_half;
    }

    public void setA_half(String a_half)
    {
        this.a_half = a_half;
    }

    public String getDatetime()
    {
        return datetime;
    }

    public void setDatetime(String datetime)
    {
        this.datetime = datetime;
    }

    public String getH_yellow()
    {
        return h_yellow;
    }

    public void setH_yellow(String h_yellow)
    {
        this.h_yellow = h_yellow;
    }

    public String getH_red()
    {
        return h_red;
    }

    public void setH_red(String h_red)
    {
        this.h_red = h_red;
    }

    public String getH_name()
    {
        return h_name;
    }

    public void setH_name(String h_name)
    {
        this.h_name = h_name;
    }

    public String getH_score()
    {
        return h_score;
    }

    public void setH_score(String h_score)
    {
        this.h_score = h_score;
    }

    public String getA_score()
    {
        return a_score;
    }

    public void setA_score(String a_score)
    {
        this.a_score = a_score;
    }

    public String getA_name()
    {
        return a_name;
    }

    public void setA_name(String a_name)
    {
        this.a_name = a_name;
    }

    public String getA_red()
    {
        return a_red;
    }

    public void setA_red(String a_red)
    {
        this.a_red = a_red;
    }

    public String getA_yellow()
    {
        return a_yellow;
    }

    public void setA_yellow(String a_yellow)
    {
        this.a_yellow = a_yellow;
    }

    public String getD1()
    {
        return d1;
    }

    public void setD1(String d1)
    {
        this.d1 = d1;
    }

    public String getD2()
    {
        return d2;
    }

    public void setD2(String d2)
    {
        this.d2 = d2;
    }

    public String getD3()
    {
        return d3;
    }

    public void setD3(String d3)
    {
        this.d3 = d3;
    }

    public String getD4()
    {
        return d4;
    }

    public void setD4(String d4)
    {
        this.d4 = d4;
    }

    public String getD5()
    {
        return d5;
    }

    public void setD5(String d5)
    {
        this.d5 = d5;
    }

    public String getD6()
    {
        return d6;
    }

    public void setD6(String d6)
    {
        this.d6 = d6;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public String getJingcai()
    {
        return jingcai;
    }

    public void setJingcai(String jingcai)
    {
        this.jingcai = jingcai;
    }

    public String getCurrent()
    {
        return current;
    }

    public void setCurrent(String current)
    {
        this.current = current;
    }

    public String getH_rank()
    {
        return h_rank;
    }

    public void setH_rank(String h_rank)
    {
        this.h_rank = h_rank;
    }

    public String getA_rank()
    {
        return a_rank;
    }

    public void setA_rank(String a_rank)
    {
        this.a_rank = a_rank;
    }

    public int getA()
    {
        return a;
    }

    public void setA(int a)
    {
        this.a = a;
    }

    public String getIs_follow()
    {
        return is_follow;
    }

    public void setIs_follow(String is_follow)
    {
        this.is_follow = is_follow;
    }
}
