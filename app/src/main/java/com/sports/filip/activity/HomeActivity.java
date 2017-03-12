package com.sports.filip.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.awhh.everyenjoy.library.base.eventbus.EventCenter;
import com.awhh.everyenjoy.library.base.widget.MainNavigateTabBar;
import com.bumptech.glide.Glide;
import com.sports.filip.EventCode;
import com.sports.filip.R;
import com.sports.filip.activity.account.MineInfoActivity;
import com.sports.filip.activity.base.BaseActivity;
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

    @Override
    protected String getTitleString() {
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
        if(center.getEventCode() == EventCode.CODE_UPDATE_PHOTO){
            Glide.with(this).load((String) center.getData()).error(R.drawable.icon_default_user).into(ivUserPhoto);
        } else if(center.getEventCode() == EventCode.CODE_LOGINSUCCESS){
            UserResponse response = CacheHelper.getUser();
            if(response == null){
                ivUserPhoto.setImageResource(R.drawable.icon_default_user);
            }else{
                Glide.with(this).load(response.getAvatar()).error(R.drawable.icon_default_user).into(ivUserPhoto);
            }
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewAndData() {
        UserResponse userResponse = CacheHelper.getUser();
        if(userResponse != null){
            Glide.with(this).load(userResponse.getAvatar()).into(ivUserPhoto);
        }
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        mainTabBar.setTabSelectListener(new MainNavigateTabBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(MainNavigateTabBar.ViewHolder holder) {
                mainTabBar.setCurrentSelectedTab(holder.tabIndex);
                        
                setCurrentTitle(holder.tabIndex);
            }
        });
        setCurrentTitle(0);
        
    }

    void setCurrentTitle(int tabIndex) {
        switch (tabIndex) {
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivUserPhoto:
                readyGo(MineInfoActivity.class);
                break;
            case R.id.tvSignin:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存当前选中的选项状态
        mainTabBar.onSaveInstanceState(outState);
    }

    @Override
    public void setTitle(CharSequence title) {
        //super.setTitle(title);
        tvHomeTitle.setText(title);
    }
}
