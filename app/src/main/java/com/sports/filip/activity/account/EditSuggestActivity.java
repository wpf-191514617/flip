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

public class EditSuggestActivity extends BaseActivity
{
    @Bind(R.id.etFeedBack)
    EditText etFeedBack;

    private String suggest;

    @Override
    protected String getTitleString()
    {
        return "个人简介";
    }

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.activity_feedback;
    }

    @Override
    protected void getBundleExtras(Bundle extras)
    {
        suggest = extras.getString("suggest", "");
    }

    @Override
    protected void initViewAndData()
    {
        etFeedBack.setHint("请填写您的个人简介...(最多200字)");
        etFeedBack.setText(suggest);
        etFeedBack.setSelection(suggest.length());
        
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                if(item.getItemId() == R.id.done)
                {
                    String suggest = etFeedBack.getText().toString();
                    if (StringUtils.isEmpty(suggest))
                    {
                        showToastShort("简介不能为空！");
                        return true;
                    }
                    uploadSuggest(suggest);
                    /*Intent intent = new Intent();
                    intent.putExtra("suggest" , etFeedBack.getText().toString());
                    setResult(RESULT_OK , intent);
                    finish();*/
                    return true;
                }
                return false;
            }
        });
        
    }

    private void uploadSuggest(final String suggest)
    {
        showLoadingDialog();
        OkHttpUtils.getInstance().post().tag(this)
                .url(Constants.BASEURL + "index.php?g=app&m=user&a=edit_user")
                .addParams("id" , CacheHelper.getUserId())
                .addParams("signature" , suggest)
                .build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {
                //dismissLoadingDialog();
                showToastShort("修改简介失败！");
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
                    userResponse.setSignature(suggest);
                    CacheHelper.saveCurrentUser(userResponse);
                    EventCenter center = new EventCenter(
                            EventCode.CODE_UPDATE_SUGGEST , suggest);
                    EventBus.getDefault().post(center);
                    finish();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_commit,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
