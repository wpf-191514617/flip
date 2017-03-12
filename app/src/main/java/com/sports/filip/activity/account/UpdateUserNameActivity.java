package com.sports.filip.activity.account;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.awhh.everyenjoy.library.base.eventbus.EventCenter;
import com.awhh.everyenjoy.library.base.util.StringUtils;
import com.awhh.everyenjoy.library.http.OkHttpUtils;
import com.awhh.everyenjoy.library.http.callback.StringCallback;
import com.awhh.everyenjoy.library.http.utils.GsonUtils;
import com.sports.filip.Constants;
import com.sports.filip.EventCode;
import com.sports.filip.R;
import com.sports.filip.activity.base.BaseActivity;
import com.sports.filip.entity.BaseResponse;
import com.sports.filip.entity.response.UserResponse;
import com.sports.filip.util.CacheHelper;

import butterknife.Bind;
import de.greenrobot.event.EventBus;
import okhttp3.Call;

/**
 * author:pengfei
 * date:2016/11/26
 * Email:15291967179@163.com
 */

public class UpdateUserNameActivity extends BaseActivity
{
    @Bind(R.id.etUserName)
    EditText etUserName;
    
    private String userName;

    @Override
    protected String getTitleString() {
        return "修改用户昵称";
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_update_username;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        userName = extras.getString("userName" , "");
    }

    @Override
    protected void initViewAndData() {
        etUserName.setText(userName);
        etUserName.setSelection(userName.length());
        
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                
                if(item.getItemId() == R.id.done){
                    String userNiceName = etUserName.getText().toString();
                    if(StringUtils.isEmpty(userNiceName)){
                        showToastShort("输入的昵称不能为空！");
                        return true;
                    }
                    updateUserName(userNiceName);
                    /*Intent intent =new Intent();
                    intent.putExtra("userName", etUserName.getText().toString());
                    setResult(RESULT_OK , intent);
                    finish();*/
                    return true;
                }
                
                return false;
            }
        });
        
    }

    private void updateUserName(final String userNiceName)
    {
        showLoadingDialog();
        OkHttpUtils.getInstance().post().tag(this)
                .url(Constants.BASEURL + "index.php?g=app&m=user&a=edit_user")
                .addParams("id" , CacheHelper.getUserId())
                .addParams("user_nicename" , userNiceName)
                .build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {
                dismissLoadingDialog();
                showToastShort("修改昵称失败！");
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

                UserResponse userResponse = CacheHelper.getUser();
                if(userResponse != null)
                {
                    userResponse.setUser_nicename(userNiceName);
                    CacheHelper.saveCurrentUser(userResponse);
                    EventCenter center = new EventCenter(
                            EventCode.CODE_UPDATE_NICENAME , userNiceName);
                    EventBus.getDefault().post(center);
                    finish();
                }
                
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_commit , menu);
        return super.onCreateOptionsMenu(menu);
    }
}
