package com.sports.filip.entity.response;

import java.util.List;

/**
 * Created by pk on 2017/3/27.
 */

public class BasketballExponeEntity {


    /**
     * xid : 170327301
     * league : NBA
     * color : FF0000
     * mtime : 2017,03,28,07,30,00
     * hteam : 纽约尼克斯
     * ateam : 底特律活塞
     * h1 : 0
     * h2 : 0
     * h3 : 0
     * h4 : 0
     * h5 : 0
     * a1 : 0
     * a2 : 0
     * a3 : 0
     * a4 : 0
     * a5 : 0
     * hscore : 0
     * ascore : 0
     * halfdiff :
     * totaldiff :
     * totalscore :
     * hrank : 東13
     * arank : 東10
     * op : [{"mdate":"20170327","id":"170327301","s8_dxf_sp0":"","s8_dxf_sp3":"","s8_dxf_bet":"","s8_rfsf_sp0":"","s8_rfsf_sp3":"","s8_rfsf_bet":""}]
     */

    private String xid;
    private String league;
    private String color;
    private String mtime;
    private String hteam;
    private String ateam;
    private String h1;
    private String h2;
    private String h3;
    private String h4;
    private String h5;
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private String a5;
    private String hscore;
    private String ascore;
    private String halfdiff;
    private String totaldiff;
    private String totalscore;
    private String hrank;
    private String arank;
    private List<OpBean> op;

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getHteam() {
        return hteam;
    }

    public void setHteam(String hteam) {
        this.hteam = hteam;
    }

    public String getAteam() {
        return ateam;
    }

    public void setAteam(String ateam) {
        this.ateam = ateam;
    }

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public String getH2() {
        return h2;
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    public String getH3() {
        return h3;
    }

    public void setH3(String h3) {
        this.h3 = h3;
    }

    public String getH4() {
        return h4;
    }

    public void setH4(String h4) {
        this.h4 = h4;
    }

    public String getH5() {
        return h5;
    }

    public void setH5(String h5) {
        this.h5 = h5;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getA4() {
        return a4;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

    public String getA5() {
        return a5;
    }

    public void setA5(String a5) {
        this.a5 = a5;
    }

    public String getHscore() {
        return hscore;
    }

    public void setHscore(String hscore) {
        this.hscore = hscore;
    }

    public String getAscore() {
        return ascore;
    }

    public void setAscore(String ascore) {
        this.ascore = ascore;
    }

    public String getHalfdiff() {
        return halfdiff;
    }

    public void setHalfdiff(String halfdiff) {
        this.halfdiff = halfdiff;
    }

    public String getTotaldiff() {
        return totaldiff;
    }

    public void setTotaldiff(String totaldiff) {
        this.totaldiff = totaldiff;
    }

    public String getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(String totalscore) {
        this.totalscore = totalscore;
    }

    public String getHrank() {
        return hrank;
    }

    public void setHrank(String hrank) {
        this.hrank = hrank;
    }

    public String getArank() {
        return arank;
    }

    public void setArank(String arank) {
        this.arank = arank;
    }

    public List<OpBean> getOp() {
        return op;
    }

    public void setOp(List<OpBean> op) {
        this.op = op;
    }

    public static class OpBean {
        /**
         * mdate : 20170327
         * id : 170327301
         * s8_dxf_sp0 :
         * s8_dxf_sp3 :
         * s8_dxf_bet :
         * s8_rfsf_sp0 :
         * s8_rfsf_sp3 :
         * s8_rfsf_bet :
         */

        private String mdate;
        private String id;
        private String s8_dxf_sp0;
        private String s8_dxf_sp3;
        private String s8_dxf_bet;
        private String s8_rfsf_sp0;
        private String s8_rfsf_sp3;
        private String s8_rfsf_bet;

        public String getMdate() {
            return mdate;
        }

        public void setMdate(String mdate) {
            this.mdate = mdate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getS8_dxf_sp0() {
            return s8_dxf_sp0;
        }

        public void setS8_dxf_sp0(String s8_dxf_sp0) {
            this.s8_dxf_sp0 = s8_dxf_sp0;
        }

        public String getS8_dxf_sp3() {
            return s8_dxf_sp3;
        }

        public void setS8_dxf_sp3(String s8_dxf_sp3) {
            this.s8_dxf_sp3 = s8_dxf_sp3;
        }

        public String getS8_dxf_bet() {
            return s8_dxf_bet;
        }

        public void setS8_dxf_bet(String s8_dxf_bet) {
            this.s8_dxf_bet = s8_dxf_bet;
        }

        public String getS8_rfsf_sp0() {
            return s8_rfsf_sp0;
        }

        public void setS8_rfsf_sp0(String s8_rfsf_sp0) {
            this.s8_rfsf_sp0 = s8_rfsf_sp0;
        }

        public String getS8_rfsf_sp3() {
            return s8_rfsf_sp3;
        }

        public void setS8_rfsf_sp3(String s8_rfsf_sp3) {
            this.s8_rfsf_sp3 = s8_rfsf_sp3;
        }

        public String getS8_rfsf_bet() {
            return s8_rfsf_bet;
        }

        public void setS8_rfsf_bet(String s8_rfsf_bet) {
            this.s8_rfsf_bet = s8_rfsf_bet;
        }
    }
}
