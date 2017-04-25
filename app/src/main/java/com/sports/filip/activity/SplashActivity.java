package com.sports.filip.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.awhh.everyenjoy.library.http.OkHttpUtils;
import com.awhh.everyenjoy.library.http.callback.StringCallback;
import com.awhh.everyenjoy.library.http.utils.GsonUtils;
import com.sports.filip.Constants;
import com.sports.filip.R;
import com.sports.filip.activity.base.BaseActivity;
import com.sports.filip.activity.callback.OnConnectRongIMCallBack;
import com.sports.filip.entity.LoginUser;
import com.sports.filip.entity.response.RegisterResponse;
import com.sports.filip.manager.UserManager;
import com.sports.filip.util.CacheHelper;

import okhttp3.Call;

/**
 * author:pengfei
 * date:2017/3/19
 * Email:15291967179@163.com
 */

public class SplashActivity extends BaseActivity
{
    @Override
    protected String getTitleString()
    {
        return null;
    }

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.layout_splash;
    }

    @Override
    protected void getBundleExtras(Bundle extras)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    protected void initViewAndData()
    {
        LoginUser user = CacheHelper.getCurrentLoginUserInfo();
        if (user.isEmpty())
            splash();
        else
            toLogin(user);
    }

    private void toLogin(final LoginUser user)
    {
        OkHttpUtils.getInstance().post().tag(this)
                .url(Constants.BASEURL + "index.php?g=app&m=user&a=login")
                .addParams("username", user.getName())
                .addParams("password", user.getPass())
                .build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {
                showToastShort("登录失败！");
                CacheHelper.saveCurrentUser("");
                splash();
            }

            @Override
            public void onResponse(String string, int id)
            {
                RegisterResponse response =
                        GsonUtils.gsonToBean(string, RegisterResponse.class);
                if (response.getStatus() != 1)
                {
                    showToastShort(response.getError());
                    CacheHelper.saveCurrentUser("");
                    splash();
                    return;
                }
                CacheHelper.saveCurrentUser(response.getUser());
                CacheHelper.saveCurrentLoginUserInfo(user.getName(), user.getPass());
                connect(response.getUser().getToken());
            }
        });
    }


    private void connect(String string)
    {
        UserManager.getInstance().signRongIM(string, new OnConnectRongIMCallBack()
        {
            @Override
            public void onSuccess()
            {

                showToastShort("登录成功！");
                toMain();
            }

            @Override
            public void onFailed()
            {
                showToastShort("登录失败！");
                CacheHelper.saveCurrentUser("");
                splash();
            }
        });
    }

    private void splash()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                toMain();
            }
        }, 2000);
    }

    private void toMain()
    {
        readyGo(HomeActivity.class);
        finish();
    }

    @Override
    protected void onDestroy()
    {
        OkHttpUtils.getInstance().cancelTag(this);
        super.onDestroy();
    }
}
