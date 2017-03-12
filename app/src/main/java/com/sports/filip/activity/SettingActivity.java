package com.sports.filip.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.awhh.everyenjoy.library.base.eventbus.EventCenter;
import com.sports.filip.EventCode;
import com.sports.filip.R;
import com.sports.filip.activity.base.BaseActivity;
import com.sports.filip.util.CacheHelper;

import butterknife.Bind;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by pengfei on 2016/11/18.
 */

public class SettingActivity extends BaseActivity
{
    @Bind(R.id.layoutAboutUs)
    LinearLayout layoutAboutUs;
    @Bind(R.id.tvCurrentVersion)
    TextView tvCurrentVersion;
    @Bind(R.id.layoutCheckoutVersion)
    LinearLayout layoutCheckoutVersion;
    @Bind(R.id.layoutLogout)
    LinearLayout layoutLogout;
    
    @Override
    protected String getTitleString() {
        return "设置";
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewAndData() {

    }


    @OnClick({R.id.layoutLogout , R.id.layoutAboutUs, R.id.layoutCheckoutVersion})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layoutLogout:
                showLogoutDialog();
                break;
            case R.id.layoutAboutUs:
                break;
            case R.id.layoutCheckoutVersion:
                break;
        }
    }

    private void showLogoutDialog()
    {
        new MaterialDialog.Builder(this)
                .content("确定退出？")
                .positiveText("确定")
                .negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback()
                {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
                    {
                        CacheHelper.saveCurrentUser("");
                        EventBus.getDefault().post(new EventCenter(EventCode.CODE_LOGINSUCCESS));
                        finish();
                    }
                }).show();
    }
}
