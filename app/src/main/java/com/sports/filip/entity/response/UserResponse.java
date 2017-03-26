package com.sports.filip.entity.response;

/**
 * author:pengfei
 * date:2017/1/16
 * Email:15291967179@163.com
 */

public class UserResponse
{

    /**
     * id : 2
     * avatar : http://59.188.133.108
     * user_pass : ###15462a36cfcede5a8d9a1727ee94b042
     * user_login : 15529000512
     * user_nicename : 用户名_2
     * user_email : 
     * user_url : 
     * score : 0
     * introduction : 
     */

    private String id;
    private String avatar;
    private String user_pass;
    private String user_login;
    private String user_nicename;
    private String user_email;
    private String user_url;
    private String score;
    private String signature;
    private String token;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getUser_pass()
    {
        return user_pass;
    }

    public void setUser_pass(String user_pass)
    {
        this.user_pass = user_pass;
    }

    public String getUser_login()
    {
        return user_login;
    }

    public void setUser_login(String user_login)
    {
        this.user_login = user_login;
    }

    public String getUser_nicename()
    {
        return user_nicename;
    }

    public void setUser_nicename(String user_nicename)
    {
        this.user_nicename = user_nicename;
    }

    public String getUser_email()
    {
        return user_email;
    }

    public void setUser_email(String user_email)
    {
        this.user_email = user_email;
    }

    public String getUser_url()
    {
        return user_url;
    }

    public void setUser_url(String user_url)
    {
        this.user_url = user_url;
    }

    public String getScore()
    {
        return score;
    }

    public void setScore(String score)
    {
        this.score = score;
    }

    public String getSignature()
    {
        return signature;
    }

    public void setSignature(String signature)
    {
        this.signature = signature;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }
}
