package com.sports.filip.entity.response;

import com.sports.filip.entity.BaseResponse;

/**
 * author:pengfei
 * date:2017/1/16
 * Email:15291967179@163.com
 */

public class RegisterResponse extends BaseResponse
{
    private UserResponse user;

    public UserResponse getUser()
    {
        return user;
    }

    public void setUser(UserResponse user)
    {
        this.user = user;
    }
}
