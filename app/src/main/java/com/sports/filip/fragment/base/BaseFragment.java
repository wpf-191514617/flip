package com.sports.filip.fragment.base;

import android.view.View;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.awhh.everyenjoy.library.base.eventbus.EventCenter;
import com.awhh.everyenjoy.library.base.net.NetUtils;
import com.awhh.everyenjoy.library.base.view.BaseView;
import com.sports.filip.R;


/**
 * 作者：王鹏飞
 * 创建时间：2016/1/5 09:12
 * 邮箱：15291967179@163.com
 * 描述：
 */
public abstract class BaseFragment extends com.awhh.everyenjoy.library.base.fragment.BaseFragment implements BaseView
{

    private MaterialDialog loadingMaterialDialog;

    protected void showLoadingDialog() {
        loadingMaterialDialog = new MaterialDialog.Builder(getActivity())
                .content(R.string.please_wait)
                .contentGravity(GravityEnum.START)
                .progress(true, 0)
                .cancelable(false)
                .progressIndeterminateStyle(false).show();
    }

    protected void dismissLoadingDialog(){
        if(loadingMaterialDialog != null && loadingMaterialDialog.isShowing())
            loadingMaterialDialog.dismiss();
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void onFirstUserInVisible() {

    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    /**
     * 点击屏幕重新加载方法
     */
    protected void onReloadThe(){}
    

    @Override
    public void showLoading(String msg)
    {
        toggleShowLoading(true, msg);
    }

    @Override
    public void hideLoading()
    {
        toggleShowLoading(false , null);
    }

    @Override
    public void showError(String msg)
    {
        toggleShowError(true, msg, new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onReloadThe();
            }
        });
    }

    @Override
    public void showException(String msg)
    {
        showError(msg);
    }

    @Override
    public void showNetError()
    {
        toggleNetworkError(true, new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onReloadThe();
            }
        });
    }

    @Override
    public void showNormal()
    {
        toggleShowEmpty(true, null, new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onReloadThe();
            }
        });
    }

    protected boolean checkNet(){
        if(!NetUtils.isNetworkConnected(getActivity())){
            showNetError();
            return false;
        }else{
            return true;
        }
    }
    
}
