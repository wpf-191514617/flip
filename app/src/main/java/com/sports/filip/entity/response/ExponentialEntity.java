package com.sports.filip.entity.response;

import java.util.List;

/**
 * author:pengfei
 * date:2017/3/26
 * Email:15291967179@163.com
 */

public class ExponentialEntity
{


    /**
     * mid : 1198392
     * league_name : 欧洲预选
     * mtime : 01:00
     * h_name : 瑞典
     * a_name : 白俄罗斯
     * h_score : 2
     * a_score : 0
     * h_rank : 45
     * a_rank : 72
     * h_red : 0
     * a_red : 0
     * h_yellow : 1
     * a_yellow : 0
     * yp : [{"d1":"Bet365","d2":"盘口1","d3":"1.08","d4":"一球/球半","d5":"0.85","d6":"0.90","d7":"半球/一球","d8":"0.95"}]
     */

    private String mid;
    private String league_name;
    private String mtime;
    private String h_name;
    private String a_name;
    private String h_score;
    private String a_score;
    private String h_rank;
    private String a_rank;
    private String h_red;
    private String a_red;
    private String h_yellow;
    private String a_yellow;
    /**
     * d1 : Bet365
     * d2 : 盘口1
     * d3 : 1.08
     * d4 : 一球/球半
     * d5 : 0.85
     * d6 : 0.90
     * d7 : 半球/一球
     * d8 : 0.95
     */

    private List<YpBean> yp;

    public String getMid()
    {
        return mid;
    }

    public void setMid(String mid)
    {
        this.mid = mid;
    }

    public String getLeague_name()
    {
        return league_name;
    }

    public void setLeague_name(String league_name)
    {
        this.league_name = league_name;
    }

    public String getMtime()
    {
        return mtime;
    }

    public void setMtime(String mtime)
    {
        this.mtime = mtime;
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

    public String getH_red()
    {
        return h_red;
    }

    public void setH_red(String h_red)
    {
        this.h_red = h_red;
    }

    public String getA_red()
    {
        return a_red;
    }

    public void setA_red(String a_red)
    {
        this.a_red = a_red;
    }

    public String getH_yellow()
    {
        return h_yellow;
    }

    public void setH_yellow(String h_yellow)
    {
        this.h_yellow = h_yellow;
    }

    public String getA_yellow()
    {
        return a_yellow;
    }

    public void setA_yellow(String a_yellow)
    {
        this.a_yellow = a_yellow;
    }

    public List<YpBean> getYp()
    {
        return yp;
    }

    public void setYp(List<YpBean> yp)
    {
        this.yp = yp;
    }

    public static class YpBean
    {
        private String d1;
        private String d2;
        private String d3;
        private String d4;
        private String d5;
        private String d6;
        private String d7;
        private String d8;

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

        public String getD7()
        {
            return d7;
        }

        public void setD7(String d7)
        {
            this.d7 = d7;
        }

        public String getD8()
        {
            return d8;
        }

        public void setD8(String d8)
        {
            this.d8 = d8;
        }
    }
}
