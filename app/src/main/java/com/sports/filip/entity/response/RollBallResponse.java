package com.sports.filip.entity.response;

import java.util.List;

/**
 * Created by pengfei on 2016/10/15.
 */

public class RollBallResponse
{


    /**
     * status : 1
     * error : 
     * list : [{"id":"1219107","time":"10-15 15:00","league":"中超","color":"#0066FF","h_name":"延边富德[9]","a_name":"天津泰达亿利[13]","minute":"下","h_score":"1","a_score":"1","rangfen":"平手","h_rang":"主","h_rang1":"0.68","a_rang":"客","a_rang1":"1.21","da":"大","da1":"2.5","xiao":"小","xiao1":"2.5","h_dx":"大","h_dx1":"2.08","a_dx":"小","a_dx1":"0.32"},{"id":"1212319","time":"10-15 15:00","league":"韩挑K联","color":"#0099cc","h_name":"富川FC[4]","a_name":"FC安养[9]","minute":"下","h_score":"1","a_score":"0","rangfen":"一/球半","h_rang":"主","h_rang1":"1.28","a_rang":"客","a_rang1":"0.62","da":"大","da1":"2/2.5","xiao":"小","xiao1":"2/2.5","h_dx":"大","h_dx1":"0.87","a_dx":"小","a_dx1":"0.95"}]
     */

    private int status;
    private String error;
    /**
     * id : 1219107
     * time : 10-15 15:00
     * league : 中超
     * color : #0066FF
     * h_name : 延边富德[9]
     * a_name : 天津泰达亿利[13]
     * minute : 下
     * h_score : 1
     * a_score : 1
     * rangfen : 平手
     * h_rang : 主
     * h_rang1 : 0.68
     * a_rang : 客
     * a_rang1 : 1.21
     * da : 大
     * da1 : 2.5
     * xiao : 小
     * xiao1 : 2.5
     * h_dx : 大
     * h_dx1 : 2.08
     * a_dx : 小
     * a_dx1 : 0.32
     */

    private List<ListBean> list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String id;
        private String time;
        private String league;
        private String color;
        private String h_name;
        private String a_name;
        private String minute;
        private String h_score;
        private String a_score;
        private String rangfen;
        private String h_rang;
        private String h_rang1;
        private String a_rang;
        private String a_rang1;
        private String da;
        private String da1;
        private String xiao;
        private String xiao1;
        private String h_dx;
        private String h_dx1;
        private String a_dx;
        private String a_dx1;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
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

        public String getH_name() {
            return h_name;
        }

        public void setH_name(String h_name) {
            this.h_name = h_name;
        }

        public String getA_name() {
            return a_name;
        }

        public void setA_name(String a_name) {
            this.a_name = a_name;
        }

        public String getMinute() {
            return minute;
        }

        public void setMinute(String minute) {
            this.minute = minute;
        }

        public String getH_score() {
            return h_score;
        }

        public void setH_score(String h_score) {
            this.h_score = h_score;
        }

        public String getA_score() {
            return a_score;
        }

        public void setA_score(String a_score) {
            this.a_score = a_score;
        }

        public String getRangfen() {
            return rangfen;
        }

        public void setRangfen(String rangfen) {
            this.rangfen = rangfen;
        }

        public String getH_rang() {
            return h_rang;
        }

        public void setH_rang(String h_rang) {
            this.h_rang = h_rang;
        }

        public String getH_rang1() {
            return h_rang1;
        }

        public void setH_rang1(String h_rang1) {
            this.h_rang1 = h_rang1;
        }

        public String getA_rang() {
            return a_rang;
        }

        public void setA_rang(String a_rang) {
            this.a_rang = a_rang;
        }

        public String getA_rang1() {
            return a_rang1;
        }

        public void setA_rang1(String a_rang1) {
            this.a_rang1 = a_rang1;
        }

        public String getDa() {
            return da;
        }

        public void setDa(String da) {
            this.da = da;
        }

        public String getDa1() {
            return da1;
        }

        public void setDa1(String da1) {
            this.da1 = da1;
        }

        public String getXiao() {
            return xiao;
        }

        public void setXiao(String xiao) {
            this.xiao = xiao;
        }

        public String getXiao1() {
            return xiao1;
        }

        public void setXiao1(String xiao1) {
            this.xiao1 = xiao1;
        }

        public String getH_dx() {
            return h_dx;
        }

        public void setH_dx(String h_dx) {
            this.h_dx = h_dx;
        }

        public String getH_dx1() {
            return h_dx1;
        }

        public void setH_dx1(String h_dx1) {
            this.h_dx1 = h_dx1;
        }

        public String getA_dx() {
            return a_dx;
        }

        public void setA_dx(String a_dx) {
            this.a_dx = a_dx;
        }

        public String getA_dx1() {
            return a_dx1;
        }

        public void setA_dx1(String a_dx1) {
            this.a_dx1 = a_dx1;
        }
    }
}
