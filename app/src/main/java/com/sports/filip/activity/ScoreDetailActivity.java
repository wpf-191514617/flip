package com.sports.filip.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.awhh.everyenjoy.library.base.activity.BaseWebActivity;
import com.awhh.everyenjoy.library.base.eventbus.EventCenter;
import com.sports.filip.Constants;
import com.sports.filip.EventCode;
import com.sports.filip.activity.account.LoginActivity;
import com.sports.filip.activity.base.BaseAgreement;
import com.sports.filip.callback.OnChatLoginListener;
import com.sports.filip.callback.OnMatchLiveCallBackListener;
import com.sports.filip.util.CacheHelper;

/**
 * author:pengfei
 * date:2017/4/15
 * Email:15291967179@163.com
 */

public class ScoreDetailActivity extends BaseAgreement implements OnMatchLiveCallBackListener,
        OnChatLoginListener
{

    private String title;
    private String mId;

    private int RS = 0;

    @Override
    protected boolean isRegisterEventBus()
    {
        return true;
    }

    @Override
    protected void getBundleExtras(Bundle extras)
    {
        super.getBundleExtras(extras);
        title = extras.getString(BaseWebActivity.BUNDLE_KEY_TITLE);
        mId = extras.getString(BaseWebActivity.BUNDLE_KEY_URL);
    }

    @Override
    protected String getTitleString()
    {
        return title;
    }

    @Override
    protected String getLoadUrl()
    {
        return Constants.BASEURL + "index.php?g=app&m=match&a=match_content&id=" + mId +
                "&userid=" + CacheHelper.getUserId() + "&rs=" + RS;
    }

    @Override
    public void onChatLogin()
    {
        RS = 4;
        readyGo(LoginActivity.class);
    }


    @Override
    public void onMatchLive(String href)
    {
        String[] hrefs = href.split(",");
        if (hrefs.length > 1)
        {
            showSelectLive(hrefs);
        } else
        {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri content_url = Uri.parse(href);
            intent.setData(content_url);
            startActivity(intent);
        }
    }

    private void showSelectLive(final String[] hrefs) {

        String[] items = new String[hrefs.length];
        for (int i = 0;i < hrefs.length;i++)
        {
            items[i] = "播放地址 - " + (i + 1);
        }

        new MaterialDialog.Builder(this).title("请选择：")
                .items(items)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        Uri content_url = Uri.parse(hrefs[position]);
                        intent.setData(content_url);
                        startActivity(intent);
                    }
                }).show();

    }
    

    @Override
    protected void onEventComming(EventCenter center)
    {
        super.onEventComming(center);
        if (center.getEventCode() == EventCode.CODE_LOGINSUCCESS)
        {
            if (RS == 4)
                webBLLayout.loadUrl(getLoadUrl());
        }
    }
}
