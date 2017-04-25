package com.sports.filip.fragment.home;

import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.awhh.everyenjoy.library.base.util.StringUtils;
import com.awhh.everyenjoy.library.http.OkHttpUtils;
import com.awhh.everyenjoy.library.http.utils.GsonUtils;
import com.sports.filip.Constants;
import com.sports.filip.R;
import com.sports.filip.activity.account.LoginActivity;
import com.sports.filip.fragment.base.BaseFragment;
import com.sports.filip.util.CacheHelper;

import java.io.IOException;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;
import okhttp3.Response;

/**
 * Created by pengfei on 2016/11/14.
 */

public class ChatFragment extends BaseFragment implements RongIM.UserInfoProvider
{

    private Handler mHandler = new Handler();

    @Bind(R.id.layoutfootballchat)
    LinearLayout layoutfootballchat;
    @Bind(R.id.layoutBaseketballchat)
    LinearLayout layoutBaseketballchat;

    private final String GROUP_ID_SCORE = "1111";
    private final String GROUP_ID_BASEKETBALL = "2222";

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.fragment_chat;
    }

    @Override
    protected void initViewAndData()
    {
        RongIM.setUserInfoProvider(this, true);
    }


    @OnClick({R.id.layoutfootballchat, R.id.layoutBaseketballchat})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.layoutfootballchat:
                chat(GROUP_ID_SCORE);
                break;
            case R.id.layoutBaseketballchat:
                chat(GROUP_ID_BASEKETBALL);
                break;
        }
    }

    private void chat(String groupId)
    {
        if (StringUtils.isEmpty(CacheHelper.getUserId()))
        {
            readyGo(LoginActivity.class);
            return;
        }
        if (groupId.equalsIgnoreCase(GROUP_ID_SCORE))
            RongIM.getInstance().startGroupChat(getActivity(), groupId, "足球群聊");
        else
            RongIM.getInstance().startGroupChat(getActivity(), groupId, "篮球群聊");
    }

    @Override
    public UserInfo getUserInfo(String s)
    {
        if (CacheHelper.getUserId().equalsIgnoreCase(s))
            return new UserInfo(CacheHelper.getUserId(), CacheHelper.getUser().getUser_nicename(),
                    Uri.parse(CacheHelper.getUser().getAvatar()));
        else
        {
            try
            {
                Response response = OkHttpUtils.post()
                        .tag(this)
                        .url(Constants.BASEURL
                                + "index.php?g=api&m=footballapi&a=get_user_info")
                        .addParams("id", s)
                        .build().execute();
                String res = response.body().string().toString();
                com.sports.filip.entity.response.UserInfo userInfo = GsonUtils.gsonToBean(res, com.sports.filip.entity.response.UserInfo.class);
                if (!StringUtils.isEmpty(userInfo.getUser_nicename()) &&
                        !StringUtils.isEmpty(userInfo.getAvatar()))
                    return new UserInfo(s, userInfo.getUser_nicename(), 
                            Uri.parse(Constants.BASEURL + "data/upload/avatar/"+userInfo.getAvatar()));
            } catch (IOException e)
            {
                return null;
            }
        }
        return null;
    }
}
