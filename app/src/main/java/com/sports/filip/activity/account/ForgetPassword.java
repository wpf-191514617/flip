package com.sports.filip.activity.account;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sports.filip.Constants;
import com.sports.filip.R;
import com.sports.filip.activity.base.BaseActivity;
import com.sports.filip.util.MyCountDownTimer;
import com.sports.filip.util.callback.CountTimerCallBack;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by pengfei on 2016/11/13.
 */

public class ForgetPassword extends BaseActivity implements CountTimerCallBack
{
    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etAuthCode)
    EditText etAuthCode;
    @Bind(R.id.btnSendAuthCode)
    Button btnSendAuthCode;
    @Bind(R.id.btnCommit)
    Button btnCommit;
    @Bind(R.id.tvToRegister)
    TextView tvToRegister;
    
    private MyCountDownTimer myCountDownTimer;

    @Override
    protected String getTitleString() {
        return "找回密码";
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_forgetpqssword;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewAndData() {
        
    }

    @OnClick({R.id.btnSendAuthCode, R.id.btnCommit, R.id.tvToRegister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSendAuthCode:
                sendAuthCode();
                break;
            case R.id.btnCommit:
                break;
            case R.id.tvToRegister:
                readyGoThenKill(RegisterActivity.class);
                break;
        }
    }

    private void sendAuthCode() {
        if(myCountDownTimer== null){
            myCountDownTimer = new MyCountDownTimer(Constants.CountTimer.millisInFuture , Constants.CountTimer.countDownInterval ,this);
        }
        myCountDownTimer.start();
        btnSendAuthCode.setBackgroundResource(R.drawable.bg_btn_gray);
        btnSendAuthCode.setTextColor(Color.parseColor("#7f7f7f"));
        btnSendAuthCode.setClickable(false);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        btnSendAuthCode.setText(millisUntilFinished / 1000 + "后重新发送");
    }

    @Override
    public void onFinish() {
        btnSendAuthCode.setBackgroundResource(R.drawable.bg_btn_sendcode);
        btnSendAuthCode.setTextColor(Color.parseColor("#ffffff"));
        btnSendAuthCode.setClickable(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(null == myCountDownTimer)
            return;
        myCountDownTimer.cancel();
        myCountDownTimer.onFinish();
        myCountDownTimer = null;
    }
}
