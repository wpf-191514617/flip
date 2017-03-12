package com.sports.filip.activity.account;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.awhh.everyenjoy.library.base.util.StringUtils;
import com.awhh.everyenjoy.library.http.OkHttpUtils;
import com.awhh.everyenjoy.library.http.callback.StringCallback;
import com.awhh.everyenjoy.library.http.utils.GsonUtils;
import com.sports.filip.Constants;
import com.sports.filip.R;
import com.sports.filip.activity.base.BaseActivity;
import com.sports.filip.entity.BaseResponse;
import com.sports.filip.util.CacheHelper;

import butterknife.Bind;
import okhttp3.Call;

/**
 * author:pengfei
 * date:2017/1/7
 * Email:15291967179@163.com
 */

public class UpdatePWDActivity extends BaseActivity
{
    @Bind(R.id.etOLDPwd)
    EditText etOLDPwd;
    @Bind(R.id.etNEWPwd)
    EditText etNEWPwd;
    @Bind(R.id.etOLDAGINPwd)
    EditText etOLDAGINPwd;

    @Override
    protected String getTitleString()
    {
        return "修改密码";
    }

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.activity_update_pwd;
    }

    @Override
    protected void getBundleExtras(Bundle extras)
    {

    }

    @Override
    protected void initViewAndData()
    {
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {

                if (item.getItemId() == R.id.done)
                {
                    updateUserPWD();
                    return true;
                }

                return false;
            }
        });
    }

    /**
     * 验证用户输入的密码
     */
    private void updateUserPWD()
    {
        String old = etOLDPwd.getText().toString();
        String newPwd = etNEWPwd.getText().toString();
        String newPwd1 = etOLDAGINPwd.getText().toString();
        if(!StringUtils.isPassword(old))return;
        if(!StringUtils.isPassword(newPwd))return;
        if(!newPwd1.equals(newPwd)){
            showToastShort("俩次输入的密码不一致！");
            return;
        }
        doUpdate(old , newPwd);
    }

    /**
     * 修改密码
     * @param old
     * @param newPwd
     */
    private void doUpdate(String old, String newPwd)
    {
        showLoadingDialog();
        OkHttpUtils.getInstance().post().tag(this)
                .url(Constants.BASEURL + "index.php?g=app&m=user&a=edit_password")
                .addParams("uid" , CacheHelper.getUserId())
                .addParams("old_password" ,old)
                .addParams("password" , newPwd)
                .addParams("repassword" , newPwd)
                .build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {
                dismissLoadingDialog();
                showToastShort("修改密码失败！");
            }

            @Override
            public void onResponse(String response, int id)
            {
                dismissLoadingDialog();
                BaseResponse baseResponse = GsonUtils.gsonToBean(response , BaseResponse.class);
                if(baseResponse.getStatus() != 1){
                    showToastShort(baseResponse.getError());
                    return;
                }
                showToastShort("修改密码成功！");
                finish();
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        OkHttpUtils.getInstance().cancelTag(this);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_commit, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
