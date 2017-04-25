package com.sports.filip.activity.base;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.awhh.everyenjoy.library.base.util.PreferenceUtil;
import com.awhh.everyenjoy.library.base.widget.OnLoadUrlCallBackListener;
import com.sports.filip.R;
import com.sports.filip.activity.callback.OnCloseCallBack;
import com.sports.filip.widget.ZLJBrowserLayout;

import butterknife.Bind;

/**
 * Created by pengfei on 2016/9/17.
 */
public abstract class BaseAgreement extends BaseActivity implements
        OnLoadUrlCallBackListener,
        OnCloseCallBack
{


    @Bind(R.id.webBLLayout)
    protected ZLJBrowserLayout webBLLayout;
    @Bind(R.id.layoutContent)
    LinearLayout layoutContent;

    

    @Override
    public void OnClose()
    {
        //showToastShort("postadd");
        PreferenceUtil.write("isExpert", true);
        finish();
    }

    @Override
    public void OnTitleBarStatus(boolean isVisible)
    {

    }

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.activity_agreement;
    }

    @Override
    protected void getBundleExtras(Bundle extras)
    {

    }

    @Override
    protected View getLoadingTargetView()
    {
        return layoutContent;
    }

    @Override
    public void onFinish(String a)
    {
        reStoreView();
    }

    protected abstract String getTitleString();

    @Override
    protected void initViewAndData()
    {
        setTitle(getTitleString());
        initWebListener();
        //showLoading("");
        webBLLayout.loadUrl(getLoadUrl());
    }

    protected void initWebListener()
    {
        webBLLayout.hideBrowserController();
        webBLLayout.setOnLoadUrlCallBackListener(this);
        webBLLayout.setOnCloseCallBack(this);
    }

    protected abstract String getLoadUrl();

}
