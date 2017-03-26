package com.sports.filip.activity.account;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.awhh.everyenjoy.library.base.util.StringUtils;
import com.awhh.everyenjoy.library.http.OkHttpUtils;
import com.awhh.everyenjoy.library.http.callback.StringCallback;
import com.awhh.everyenjoy.library.http.utils.GsonUtils;
import com.sports.filip.Constants;
import com.sports.filip.R;
import com.sports.filip.activity.base.BaseActivity;
import com.sports.filip.entity.response.RegisterResponse;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by pengfei on 2016/11/13.
 */

public class RegisterActivity extends BaseActivity
{
    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.etPassword2)
    EditText etPassword2;
    @Bind(R.id.cbAgree)
    CheckBox cbAgree;
    @Bind(R.id.tvUserAgree)
    TextView tvUserAgree;
    @Bind(R.id.btnRegister)
    Button btnRegister;
    @Bind(R.id.tvToLogin)
    TextView tvToLogin;

    @Override
    protected String getTitleString() {
        return "注册";
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewAndData() {
        cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    btnRegister.setBackgroundResource(R.drawable.bg_btn_login);
                    btnRegister.setClickable(false);
                } else {
                    btnRegister.setBackgroundResource(R.drawable.bg_btn_gray);
                    btnRegister.setClickable(true);
                }
            }
        });
        cbAgree.setChecked(true);
    }

    @OnClick({R.id.tvUserAgree, R.id.btnRegister, R.id.tvToLogin})
    public void onClick(View view) {
        switch (view.getId()) {
                case R.id.tvUserAgree:
                    break;
                case R.id.btnRegister:
                    String phone = etPhone.getText().toString();
                    String pwd = etPassword.getText().toString();
                    String pwd1 = etPassword2.getText().toString();
                    if (!StringUtils.isMobileNO(phone))
                    {
                        showToastShort("手机号格式错误");
                        return;
                    }
                    if(StringUtils.isEmpty(pwd)){
                       showToastShort("输入的密码不能为空");
                        return;
                    }
                    if(!pwd.equals(pwd1)){
                        showToastShort("俩次输入的密码不一致");
                        return;
                    }
                    toRegister(pwd);
                    break;
                case R.id.tvToLogin:
                    finish();
                break;
        }
    }

    private void toRegister(String pwd)
    {
        showLoadingDialog();
        OkHttpUtils.getInstance().post().tag(this)
                .url(Constants.BASEURL + "index.php?g=app&m=user&a=register")
                .addParams("mobile" , etPhone.getText().toString())
                .addParams("password" , pwd)
                .addParams("verify" , "000000")
                .build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {
                dismissLoadingDialog();
                showToastShort("注册失败！");
            }

            @Override
            public void onResponse(String string, int id)
            {
                dismissLoadingDialog();
                RegisterResponse response = GsonUtils.gsonToBean(string , RegisterResponse.class);
                if(response.getStatus() != 1){
                    showToastShort(response.getError());
                    return;
                }
                showToastShort("注册成功！");
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
}
