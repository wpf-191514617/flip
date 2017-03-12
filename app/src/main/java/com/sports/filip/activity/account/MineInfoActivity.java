package com.sports.filip.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.awhh.everyenjoy.library.base.eventbus.EventCenter;
import com.awhh.everyenjoy.library.base.util.StringUtils;
import com.bumptech.glide.Glide;
import com.sports.filip.EventCode;
import com.sports.filip.R;
import com.sports.filip.activity.CreditActivity;
import com.sports.filip.activity.FeedBackActivity;
import com.sports.filip.activity.InvatationActivity;
import com.sports.filip.activity.SettingActivity;
import com.sports.filip.activity.base.BaseActivity;
import com.sports.filip.entity.ShareEntity;
import com.sports.filip.entity.response.UserResponse;
import com.sports.filip.util.CacheHelper;
import com.sports.filip.util.ShareUtil;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pengfei on 2016/11/15.
 */

public class MineInfoActivity extends BaseActivity
{
    @Bind(R.id.civUserPhoto)
    CircleImageView civUserPhoto;
    @Bind(R.id.tvUserName)
    TextView tvUserName;
    @Bind(R.id.tvUserPlot)
    TextView tvUserPoint;
    @Bind(R.id.layoutUserInfo)
    RelativeLayout layoutUserInfo;
    @Bind(R.id.layoutAccountInfo)
    LinearLayout layoutAccountInfo;
    @Bind(R.id.layoutUserBet)
    LinearLayout layoutUserBet;
    @Bind(R.id.layoutUserFocusOn)
    LinearLayout layoutUserFocusOn;
    @Bind(R.id.layoutEarnPoints)
    LinearLayout layoutEarnPoints;
    @Bind(R.id.layoutShareTo)
    LinearLayout layoutShareTo;
    @Bind(R.id.layoutPointsFor)
    LinearLayout layoutPointsFor;
    @Bind(R.id.layoutFeedBack)
    LinearLayout layoutFeedBack;
    @Bind(R.id.layoutSetting)
    LinearLayout layoutSetting;

    @Override
    protected boolean isRegisterEventBus()
    {
        return true;
    }

    @Override
    protected void onEventComming(EventCenter center)
    {
        super.onEventComming(center);
        if(center.getEventCode() == EventCode.CODE_LOGINSUCCESS){
            initUserData();
            return;
        }

        switch (center.getEventCode())
        {
            case EventCode.CODE_UPDATE_NICENAME:
                tvUserName.setText((String) center.getData());
                break;
            case EventCode.CODE_UPDATE_PHOTO:
                Glide.with(this).load((String) center.getData()).into(civUserPhoto);
                break;
        }
        
    }

    private void initUserData()
    {
        UserResponse response = CacheHelper.getUser();
        if(response != null){
            Glide.with(this).load(response.getAvatar())
                    .error(R.drawable.icon_default_user)
                    .into(civUserPhoto);
            tvUserName.setText(response.getUser_nicename());
            tvUserPoint.setText("总积分："+response.getScore());
        } else {
            civUserPhoto.setImageResource(R.drawable.icon_default_user);
            tvUserName.setText("未登录");
            tvUserPoint.setText("总积分：0");
        }
        
    }

    @Override
    protected String getTitleString() {
        return "个人中心";
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        
    }

    @Override
    protected void initViewAndData() {
        initUserData();
    }

    @OnClick({R.id.layoutUserInfo, R.id.layoutAccountInfo, R.id.layoutUserBet, R.id.layoutUserFocusOn, R.id.layoutEarnPoints, R.id.layoutShareTo, R.id.layoutPointsFor, R.id.layoutFeedBack, R.id.layoutSetting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layoutUserInfo:
                ready(AccountInfoActivity.class);
                break;
            case R.id.layoutAccountInfo:
                ready(AccountInfoActivity.class);
                break;
            case R.id.layoutUserBet:
                break;
            case R.id.layoutUserFocusOn:
                break;
            case R.id.layoutEarnPoints:
                ready(InvatationActivity.class);
                break;
            case R.id.layoutShareTo:
                ShareUtil.getInstance(this).sharToPlatform(new ShareEntity("测试标题", "测试内容", "http://hfcn.cc/",
                        "http://f.hiphotos.baidu.com/image/h%3D200/sign=3853eb794f540923b569647ea259d1dc/50da81cb39dbb6fde784f07c0f24ab18962b3788.jpg"));
                break;
            case R.id.layoutPointsFor:
//                ready(ShoppingActivity.class);

                Intent intent = new Intent();
                intent.setClass(this, CreditActivity.class);
                intent.putExtra("navColor", "#007D2C");    //配置导航条的背景颜色，请用#ffffff长格式。
                intent.putExtra("titleColor", "#ffffff");    //配置导航条标题的颜色，请用#ffffff长格式。
                intent.putExtra("url", "http://www.duiba.com.cn/test/demoRedirectSAdfjosfdjdsa");    //配置自动登陆地址，每次需服务端动态生成。
                startActivity(intent);
                
                break;
            case R.id.layoutFeedBack:
                ready(FeedBackActivity.class);
                break;
            case R.id.layoutSetting:
                readyGo(SettingActivity.class);
                break;
        }
    }
    
    
    private void ready(Class clz){
        if(StringUtils.isEmpty(CacheHelper.getUserId())){
            readyGo(LoginActivity.class);
            return;
        }
        readyGo(clz);
    }
    
}
