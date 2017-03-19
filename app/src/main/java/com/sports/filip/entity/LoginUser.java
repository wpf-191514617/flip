package com.sports.filip.entity;

import com.awhh.everyenjoy.library.base.util.StringUtils;

/**
 * author:pengfei
 * date:2017/3/19
 * Email:15291967179@163.com
 */

public class LoginUser
{
    
    private String name;
    
    private String pass;

    public LoginUser(String name, String pass)
    {
        this.name = name;
        this.pass = pass;
    }

    public String getName()
    {
        return name;
    }

    public String getPass()
    {
        return pass;
    }
    
    public boolean isEmpty(){
        if (StringUtils.isEmpty(name))
            return true;
        if (StringUtils.isEmpty(pass))
            return true;
        return false;
    }
    
}
