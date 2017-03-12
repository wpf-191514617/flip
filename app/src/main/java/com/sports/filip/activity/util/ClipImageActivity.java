package com.sports.filip.activity.util;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.awhh.everyenjoy.library.base.eventbus.EventCenter;
import com.awhh.everyenjoy.library.base.net.NetType;
import com.sports.filip.R;
import com.sports.filip.activity.base.BaseActivity;
import com.sports.filip.widget.clip.ClipImageLayout;
import com.sports.filip.widget.clip.ClipUtil;

import java.io.ByteArrayOutputStream;

/**
 * Created by Administrator on 2015/9/19.
 */
public class ClipImageActivity extends BaseActivity
{

    public static final String KEY_PHOTO = "ui.clip.photo.key";

    private String path = "";

    private ClipImageLayout clipImageLayout;

    @Override
    protected void onReloadThe()
    {
        
    }

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.activity_clipimg;
    }

    @Override
    protected void getBundleExtras(Bundle extras)
    {
        path = extras.getString(KEY_PHOTO);
    }

    @Override
    protected void initViewAndData()
    {
        clipImageLayout = (ClipImageLayout) findViewById(R.id.mClipImageLayout);
        clipImageLayout.setImage(path);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                if(item.getItemId() == R.id.done){
                    onRightClick();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onNetworkDisConnected()
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
//        setRightText(R.string.done);
        getMenuInflater().inflate(R.menu.menu_picker , menu);
        return true;
    }

    protected void onRightClick() {
        Bitmap bitmap = clipImageLayout.clip();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        ClipUtil.setClipPhotoByte(data);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.done:
                Bitmap bitmap = clipImageLayout.clip();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();
                ClipUtil.setClipPhotoByte(data);
                setResult(RESULT_OK);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNetworkConnected(NetType type)
    {

    }

    @Override
    protected String getTitleString()
    {
        return "";
    }

    @Override
    protected View getLoadingTargetView()
    {
        return null;
    }

    @Override
    protected void onEventComming(EventCenter center)
    {

    }
}
