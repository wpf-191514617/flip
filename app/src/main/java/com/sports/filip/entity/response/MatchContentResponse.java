package com.sports.filip.entity.response;

import com.sports.filip.entity.BaseRace;

import java.util.List;

/**
 * Created by pengfei on 2016/9/29.
 */

public class MatchContentResponse extends BaseRace{


    /**
     * list : [{"id":"1286213","h_name":"波特兰伐木者","a_name":"萨普里萨","type":"1","name":"波特兰伐","odds":"1.14"},{"id":"1286213","h_name":"波特兰伐木者","a_name":"萨普里萨","type":"1","name":"平","odds":"8"},{"id":"1286213","h_name":"波特兰伐木者","a_name":"萨普里萨","type":"1","name":"萨普里萨","odds":"15"}]
     * balance : 16061
     */

    private String balance;
    /**
     * id : 1286213
     * h_name : 波特兰伐木者
     * a_name : 萨普里萨
     * type : 1
     * name : 波特兰伐
     * odds : 1.14
     */

    private List<ListBean> list;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String id;
        private String h_name;
        private String a_name;
        private String type;
        private String name;
        private String odds;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOdds() {
            return odds;
        }

        public void setOdds(String odds) {
            this.odds = odds;
        }
    }
}
