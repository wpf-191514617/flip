package com.sports.filip.entity;

/**
 * author:pengfei
 * date:2017/1/16
 * Email:15291967179@163.com
 */

public class BaseResponse
{
    private String error;
    
    private int status;

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }
}
