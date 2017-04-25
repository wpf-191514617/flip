package com.sports.filip.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.sports.filip.R;
import com.sports.filip.activity.base.BaseActivity;
import com.sports.filip.entity.ShareEntity;
import com.sports.filip.util.AppHelper;
import com.sports.filip.util.ShareUtil;
import com.zxing.CreateDCode;

import java.io.InputStream;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author:pengfei
 * date:2017/3/12
 * Email:15291967179@163.com
 */

public class InvatationActivity extends BaseActivity
{
    @Bind(R.id.ivQRCode)
    ImageView ivQRCode;
    @Bind(R.id.btnShare)
    Button btnShare;

    private String url;

    @Override
    protected String getTitleString()
    {
        return "邀请好友";
    }

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.mine_invitation1;
    }

    @Override
    protected void getBundleExtras(Bundle extras)
    {

    }

    @Override
    protected void initViewAndData()
    {
        url = AppHelper.getInvatationUrl();
        Bitmap bitmap = CreateDCode.CreateQRCode(url, 270);
        bitmap = CreateDCode.withIcon(bitmap, readBitMap(this, R.drawable.icon), 0.2f);
        ivQRCode.setImageBitmap(bitmap);
    }

    public static Bitmap readBitMap(Context context, int resId)
    {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }
    
    @OnClick(R.id.btnShare)
    public void onClick()
    {
        ShareUtil.getInstance(this).sharToPlatform(new ShareEntity("测试标题", "测试内容",
                AppHelper.getInvatationUrl(),
                "http://f.hiphotos.baidu.com/image/h%3D200/sign=3853eb794f540923b569647ea259d1dc/50da81cb39dbb6fde784f07c0f24ab18962b3788.jpg"));

    }
}
