package com.sports.filip.activity.base;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.awhh.everyenjoy.library.base.activity.BaseAppCompatActivity;
import com.awhh.everyenjoy.library.base.eventbus.EventCenter;
import com.awhh.everyenjoy.library.base.net.NetUtils;
import com.awhh.everyenjoy.library.base.view.BaseView;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.sports.filip.R;

import butterknife.ButterKnife;


/**
 * 作者：王鹏飞
 * 创建时间：2015/12/31 09:56
 * 邮箱：15291967179@163.com
 * 描述：
 */
public abstract class BaseActivity extends BaseAppCompatActivity implements BaseView
{

    protected Toolbar mToolbar;

  //  protected TextView toolbar_title;

    //protected View layoutBack;

    //protected View layoutMenu;

    //protected TextView tvRight;

    //protected ImageView ivRight;

    private MaterialDialog loadingMaterialDialog;

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isApplyKitKatTranslucency()) {
            setStatusBarTint();
        }
        setTitle(getTitleString());
    }

    protected abstract String getTitleString();

    protected void setStatusBarTint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);//通知栏所需颜色
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void onEventComming(EventCenter center) {

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        if (null != mToolbar) {
            initActionBar();
        }
    }

    protected void initActionBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected boolean isApplyKitKatTranslucency() {
        return true;
    }

    @Override
    public void showLoading(String msg) {
        toggleShowLoading(true, null);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false, null);
    }

    @Override
    public void showError(String msg) {
        toggleShowError(true, msg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReloadThe();
            }
        });
    }

    /**
     * 点击屏幕重新加载方法
     */
    protected void onReloadThe() {

    }

    @Override
    public void showException(String msg) {
        showError(msg);
    }

    @Override
    public void showNetError() {
        toggleNetworkError(true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReloadThe();
            }
        });
    }

    @Override
    public void showNormal() {
        toggleShowEmpty(true, null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReloadThe();
            }
        });
    }

    public void showNormal(String msg) {
        toggleShowEmpty(true, msg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReloadThe();
            }
        });
    }


    protected boolean checkNetStatus() {
        if (!NetUtils.isNetworkConnected(this)) {
            showNetErrorDialog();
            return false;
        } else {
            return true;
        }
    }

    protected void showLoadingDialog() {
        if(loadingMaterialDialog != null && loadingMaterialDialog.isShowing()){
            loadingMaterialDialog.dismiss();
            loadingMaterialDialog = null;
        }
                
        loadingMaterialDialog = new MaterialDialog.Builder(this)
                .content(R.string.please_wait)
                .contentGravity(GravityEnum.START)
                .progress(true, 0)
                .cancelable(false)
                .progressIndeterminateStyle(false).show();
    }

    protected void dismissLoadingDialog() {
        if (loadingMaterialDialog != null && loadingMaterialDialog.isShowing())
            loadingMaterialDialog.dismiss();
    }

    protected void showNetErrorDialog() {
        new MaterialDialog.Builder(this)
                .content("当前无网络连接，是否设置？")
                .cancelable(false)
                .positiveText("确定")
                .negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        Intent intent = null;
                        // 先判断当前系统版本  
                        if (Build.VERSION.SDK_INT > 10) {  // 3.0以上  
                            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                        } else {
                            intent = new Intent();
                            intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
                        }
                        startActivity(intent);
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
                finish();
            }
        }).show();
    }

}
