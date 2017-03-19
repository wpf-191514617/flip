package com.sports.filip.activity.account;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.awhh.everyenjoy.library.base.eventbus.EventCenter;
import com.awhh.everyenjoy.library.base.util.StringUtils;
import com.awhh.everyenjoy.library.http.OkHttpUtils;
import com.awhh.everyenjoy.library.http.callback.StringCallback;
import com.awhh.everyenjoy.library.http.utils.GsonUtils;
import com.sports.filip.Constants;
import com.sports.filip.EventCode;
import com.sports.filip.R;
import com.sports.filip.activity.base.BaseActivity;
import com.sports.filip.activity.callback.OnConnectRongIMCallBack;
import com.sports.filip.entity.response.RegisterResponse;
import com.sports.filip.manager.UserManager;
import com.sports.filip.util.CacheHelper;

import butterknife.Bind;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import okhttp3.Call;

/**
 * Created by pengfei on 2016/11/12.
 */

public class LoginActivity extends BaseActivity
{
    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.btnLogin)
    Button btnLogin;
    @Bind(R.id.tvRegister)
    TextView tvRegister;
    @Bind(R.id.tvForgetPassword)
    TextView tvForgetPassword;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewAndData() {


    }


    @Override
    protected String getTitleString() {
        return "登录";
    }

    @OnClick({R.id.btnLogin, R.id.tvRegister, R.id.tvForgetPassword})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                String phone = etPhone.getText().toString();
                String pwd = etPassword.getText().toString();
                if(!StringUtils.isMobileNO(phone))return;
                if(!StringUtils.isPassword(pwd))return;
                toLogin(phone , pwd);
                break;
            case R.id.tvRegister:
                readyGo(RegisterActivity.class);
                break;
            case R.id.tvForgetPassword:
                readyGo(ForgetPassword.class);
                break;
        }
    }

    private void toLogin(final String phone, final String pwd)
    {
        showLoadingDialog();
        OkHttpUtils.getInstance().post().tag(this)
                .url(Constants.BASEURL + "index.php?g=app&m=user&a=login")
                .addParams("username" , phone)
                .addParams("password" , pwd)
                .build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {
                dismissLoadingDialog();
                showToastShort("登录失败！");
            }

            @Override
            public void onResponse(String string, int id)
            {
                dismissLoadingDialog();
                RegisterResponse response =
                        GsonUtils.gsonToBean(string , RegisterResponse.class);
                if(response.getStatus() != 1){
                    showToastShort(response.getError());
                    return;
                }
                CacheHelper.saveCurrentUser(response.getUser());
                CacheHelper.saveCurrentLoginUserInfo(phone , pwd);
                connect("LEl1wOWjr/KHidy3zGxca48e8DIRONkQ8BWmunj6HdP/M5q2PermuGhRIZ+U8h8eD3GR49RnbCg=");
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
                EventCenter center = new EventCenter(EventCode.CODE_LOGINSUCCESS);
                EventBus.getDefault().post(center);
                finish();
            }

            @Override
            public void onFailed()
            {
                showToastShort("登录失败，请重新登录！");
            }
        });
        
        
    }

    @Override
    protected void onDestroy()
    {
        OkHttpUtils.getInstance().cancelTag(this);
        super.onDestroy();
    }
}
