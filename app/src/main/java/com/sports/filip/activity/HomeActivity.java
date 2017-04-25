package com.sports.filip.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.awhh.everyenjoy.library.base.eventbus.EventCenter;
import com.awhh.everyenjoy.library.base.util.StringUtils;
import com.awhh.everyenjoy.library.base.widget.MainNavigateTabBar;
import com.awhh.everyenjoy.library.http.OkHttpUtils;
import com.awhh.everyenjoy.library.http.callback.StringCallback;
import com.awhh.everyenjoy.library.http.utils.GsonUtils;
import com.bumptech.glide.Glide;
import com.sports.filip.Constants;
import com.sports.filip.EventCode;
import com.sports.filip.R;
import com.sports.filip.activity.account.MineInfoActivity;
import com.sports.filip.activity.base.BaseActivity;
import com.sports.filip.entity.BaseResponse;
import com.sports.filip.entity.response.UserResponse;
import com.sports.filip.fragment.home.BaseketballFragment;
import com.sports.filip.fragment.home.ChatFragment;
import com.sports.filip.fragment.home.LiveFragment;
import com.sports.filip.fragment.home.RecordFragment;
import com.sports.filip.fragment.home.ScoreFragment;
import com.sports.filip.util.CacheHelper;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

import static com.sports.filip.util.CacheHelper.getUser;

/**
 * Created by pengfei on 2016/11/13.
 */

public class HomeActivity extends BaseActivity
{

    @Bind(R.id.main_container)
    FrameLayout mainContainer;
    @Bind(R.id.mainTabBar)
    MainNavigateTabBar mainTabBar;

    private final String TAG_PAGE_SCORE = "足球";
    private final String TAG_PAGE_BASKETBALL = "篮球";
    private final String TAG_PAGE_LIVE = "直播";
    private final String TAG_PAGE_RECORD = "数据";
    private final String TAG_PAGE_CHAT = "聊吧";
    @Bind(R.id.ivUserPhoto)
    CircleImageView ivUserPhoto;
    @Bind(R.id.tvHomeTitle)
    TextView tvHomeTitle;
    @Bind(R.id.tvSignin)
    TextView tvSignin;

    private long exitTime = 0;

    @Override
    protected String getTitleString()
    {
        return TAG_PAGE_SCORE;
    }

    @Override
    protected boolean isRegisterEventBus()
    {
        return true;
    }

    @Override
    protected void onEventComming(EventCenter center)
    {
        super.onEventComming(center);
        if (center.getEventCode() == EventCode.CODE_UPDATE_PHOTO)
        {
            Glide.with(this).load((String) center.getData()).error(R.drawable.icon_default_user).into(ivUserPhoto);
        } else if (center.getEventCode() == EventCode.CODE_LOGINSUCCESS)
        {
            UserResponse response = getUser();
            initSignin(false);
            if (response == null)
            {
                ivUserPhoto.setImageResource(R.drawable.icon_default_user);
            } else
            {
                Glide.with(this).load(response.getAvatar()).error(R.drawable.icon_default_user).into(ivUserPhoto);
                checkSignin();
            }
        }
    }

    private void initSignin(boolean isSignin)
    {
        if (isSignin)
        {
            tvSignin.setClickable(true);
            tvSignin.setTextColor(getResources().getColor(R.color.white));
        } else
        {
            tvSignin.setClickable(false);
            tvSignin.setTextColor(getResources().getColor(R.color.text_hint_color));
        }
    }

    private void checkSignin()
    {
        OkHttpUtils.getInstance().post()
                .url(Constants.BASEURL + "index.php?g=api&m=footballapi&a=is_sign")
                .addParams("userid", CacheHelper.getUserId())
                .addParams("phone", getUser().getUser_login())
                .build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {

            }

            @Override
            public void onResponse(String response, int id)
            {
                //Trace.d("checksign-----" + response);
                BaseResponse baseResponse = GsonUtils.gsonToBean(
                        response, BaseResponse.class);
                if (baseResponse.getStatus() == 1)
                    initSignin(true);
                else
                    initSignin(false);
            }
        });
    }

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.activity_home;
    }

    @Override
    protected void getBundleExtras(Bundle extras)
    {

    }

    @Override
    protected void initViewAndData()
    {
        UserResponse userResponse = getUser();
        if (userResponse != null)
        {
            Glide.with(this).load(userResponse.getAvatar()).into(ivUserPhoto);
        }
        initSignin(false);
        if (!StringUtils.isEmpty(CacheHelper.getUserId()))
        {
            checkSignin();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //对应xml中的containerId
        mainTabBar.setFrameLayoutId(R.id.main_container);
        //对应xml中的navigateTabTextColor
        mainTabBar.setTabTextColor(getResources().getColor(R.color.tab_text_normal));
        //对应xml中的navigateTabSelectedTextColor
        mainTabBar.setSelectedTabTextColor(getResources().getColor(R.color.colorPrimary));

        //恢复选项状态
        mainTabBar.onRestoreInstanceState(savedInstanceState);


        //mainTabBar.onRestoreInstanceState(savedInstanceState);
        mainTabBar.addTab(ScoreFragment.class, new MainNavigateTabBar.TabParam(
                R.drawable.bottom1_icon, R.drawable.bottom1_icon_hover, TAG_PAGE_SCORE));
        mainTabBar.addTab(BaseketballFragment.class, new MainNavigateTabBar.TabParam(
                R.drawable.bottom2_icon, R.drawable.bottom2_icon_hover, TAG_PAGE_BASKETBALL));
        mainTabBar.addTab(LiveFragment.class, new MainNavigateTabBar.TabParam(
                R.drawable.bottom3_icon, R.drawable.bottom3_icon_hover, TAG_PAGE_LIVE));
        mainTabBar.addTab(RecordFragment.class, new MainNavigateTabBar.TabParam(
                R.drawable.bottom4_icon, R.drawable.bottom4_icon_hover, TAG_PAGE_RECORD));
        mainTabBar.addTab(ChatFragment.class, new MainNavigateTabBar.TabParam(
                R.drawable.bottom5_icon, R.drawable.bottom5_icon_hover, TAG_PAGE_CHAT));
        mainTabBar.setTabSelectListener(new MainNavigateTabBar.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(MainNavigateTabBar.ViewHolder holder)
            {
                mainTabBar.setCurrentSelectedTab(holder.tabIndex);

                setCurrentTitle(holder.tabIndex);
            }
        });
        setCurrentTitle(0);

    }

    void setCurrentTitle(int tabIndex)
    {
        switch (tabIndex)
        {
            case 0:
                setTitle(TAG_PAGE_SCORE);
                break;
            case 1:
                setTitle(TAG_PAGE_BASKETBALL);
                break;
            case 2:
                setTitle(TAG_PAGE_LIVE);
                break;
            case 3:
                setTitle(TAG_PAGE_RECORD);
                break;
            case 4:
                setTitle(TAG_PAGE_CHAT);
                break;
        }
    }

    @OnClick({R.id.ivUserPhoto, R.id.tvSignin})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.ivUserPhoto:
                readyGo(MineInfoActivity.class);
                break;
            case R.id.tvSignin:
                sign();
                break;
        }
    }

    private void sign()
    {
        OkHttpUtils.getInstance().post()
                .url(Constants.BASEURL + "index.php?g=api&m=footballapi&a=sign_up")
                .addParams("userid", CacheHelper.getUserId())
                .addParams("phone", getUser().getUser_login())
                .build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {
                showToastShort("签到失败！");
            }

            @Override
            public void onResponse(String response, int id)
            {
                //Trace.d("checksign-----" + response);
                BaseResponse baseResponse = GsonUtils.gsonToBean(
                        response, BaseResponse.class);
                if (baseResponse.getStatus() == 1)
                {
                    showToastShort("签到成功！");
                    initSignin(false);
                    UserResponse userResponse = CacheHelper.getUser();
                    int score = Integer.parseInt(userResponse.getScore());
                    userResponse.setScore(String.valueOf(score + 3));
                    CacheHelper.saveCurrentUser(userResponse);
                } else if (baseResponse.getStatus() == 4)
                {
                    showToastShort("签到失败！");
                } else
                {
                    initSignin(false);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        //保存当前选中的选项状态
        mainTabBar.onSaveInstanceState(outState);
    }

    @Override
    public void setTitle(CharSequence title)
    {
        tvHomeTitle.setText(title);
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                this.exitApp();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * 退出程序 
     */
    private void exitApp()
    {
        // 判断2次点击事件时间  
        if ((System.currentTimeMillis() - exitTime) > 2000)
        {
            showToastShort("再按一次退出程序！");
            exitTime = System.currentTimeMillis();
        } else
        {
            finish();
            System.exit(0);
        }
    }

    }
