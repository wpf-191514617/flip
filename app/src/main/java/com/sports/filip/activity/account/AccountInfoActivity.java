package com.sports.filip.activity.account;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acp.Acp;
import com.acp.AcpListener;
import com.acp.AcpOptions;
import com.awhh.everyenjoy.library.base.eventbus.EventCenter;
import com.awhh.everyenjoy.library.base.util.StringUtils;
import com.awhh.everyenjoy.library.http.OkHttpUtils;
import com.awhh.everyenjoy.library.http.callback.StringCallback;
import com.awhh.everyenjoy.library.http.utils.GsonUtils;
import com.awhh.everyenjoy.library.localimage.PhotoPickerActivity;
import com.awhh.everyenjoy.library.localimage.utils.PhotoPickerIntent;
import com.bumptech.glide.Glide;
import com.sports.filip.Constants;
import com.sports.filip.EventCode;
import com.sports.filip.R;
import com.sports.filip.activity.base.BaseActivity;
import com.sports.filip.activity.util.ClipImageActivity;
import com.sports.filip.entity.response.URLResponse;
import com.sports.filip.entity.response.UserResponse;
import com.sports.filip.util.BitmapUtil;
import com.sports.filip.util.CacheHelper;
import com.sports.filip.widget.clip.ClipUtil;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * author:pengfei
 * date:2016/11/26
 * Email:15291967179@163.com
 */

public class AccountInfoActivity extends BaseActivity
{
    @Bind(R.id.civUserPhoto)
    CircleImageView civUserPhoto;
    @Bind(R.id.layoutUserPhoto)
    LinearLayout layoutUserPhoto;
    @Bind(R.id.tvUserName)
    TextView tvUserName;
    @Bind(R.id.layoutUserName)
    LinearLayout layoutUserName;
    @Bind(R.id.tvUserPhone)
    TextView tvUserPhone;
    @Bind(R.id.tvUserSignature)
    TextView tvUserSignature;
    @Bind(R.id.layoutUserSignature)
    LinearLayout layoutUserSignature;
    @Bind(R.id.layoutUpdatePwd)
    LinearLayout layoutUpdatePwd;

    
    private final int REQUEST_SELECT_IMG_CODE = 3;
    private final int REQUEST_CODE_CLIP = 4;

    @Override
    protected String getTitleString()
    {
        return "个人资料";
    }

    @Override
    protected boolean isRegisterEventBus()
    {
        return true;
    }

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.activity_accountinfo;
    }

    @Override
    protected void getBundleExtras(Bundle extras)
    {

    }

    @Override
    protected void onEventComming(EventCenter center)
    {
        super.onEventComming(center);
        switch (center.getEventCode())
        {
            case EventCode.CODE_UPDATE_NICENAME:
                tvUserName.setText((String) center.getData());
                break;
            case EventCode.CODE_UPDATE_SUGGEST:
                tvUserSignature.setText((String) center.getData());
                break;
            
        }
    }

    @Override
    protected void initViewAndData()
    {
        setUserInfo();
    }

    private void setUserInfo()
    {
        UserResponse response = CacheHelper.getUser();
        if (response == null)
        {
            finish();
            return;
        }

        Glide.with(this).load(response.getAvatar())
                .error(R.drawable.icon_default_user)
                .into(civUserPhoto);
        if (!StringUtils.isEmpty(response.getUser_nicename()))
            tvUserName.setText(response.getUser_nicename());
        if (!StringUtils.isEmpty(response.getUser_login()))
            tvUserPhone.setText(response.getUser_login());
        if (!StringUtils.isEmpty(response.getSignature()))
            tvUserSignature.setText(response.getSignature());


    }

    @OnClick({R.id.layoutUserPhoto, R.id.layoutUserName, R.id.layoutUserSignature, R.id.layoutUpdatePwd})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.layoutUserPhoto:
                Acp.getInstance(AccountInfoActivity.this)
                        .request(new AcpOptions.Builder()
                                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                                .build(), new AcpListener()
                        {
                            @Override
                            public void onGranted()
                            {
                                Intent intent = new Intent(AccountInfoActivity.this, PhotoPickerActivity.class);
                                PhotoPickerIntent.setPhotoCount(intent, 1);
                                PhotoPickerIntent.setShowGif(intent, false);
                                PhotoPickerIntent.setColumn(intent, 3);
                                startActivityForResult(intent, REQUEST_SELECT_IMG_CODE);
                            }

                            @Override
                            public void onDenied(List<String> permissions)
                            {
                                showToastShort(permissions.toString() + "权限拒绝");
                                finish();
                            }
                        });
                break;
            case R.id.layoutUserName:
                Bundle bundle = new Bundle();
                bundle.putString("userName", tvUserName.getText().toString());
                readyGo(UpdateUserNameActivity.class,  bundle);
                break;
            case R.id.layoutUserSignature:
                Bundle bundle1 = new Bundle();
                bundle1.putString("suggest", tvUserSignature.getText().toString());
                readyGo(EditSuggestActivity.class, bundle1);
                break;
            case R.id.layoutUpdatePwd:
                readyGo(UpdatePWDActivity.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == REQUEST_SELECT_IMG_CODE && resultCode == RESULT_OK)
        {
            List<String> photos = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
            Bundle bundle = new Bundle();
            bundle.putString(ClipImageActivity.KEY_PHOTO, photos.get(0));
            readyGoForResult(ClipImageActivity.class, REQUEST_CODE_CLIP, bundle);
        }

        if (requestCode == REQUEST_CODE_CLIP && resultCode == RESULT_OK)
        {
//            civUserPhoto.setImageDrawable(null);
            byte[] bmpBytes = ClipUtil.getClipPhotoByte();
//            Glide.with(this).load(bmpBytes).into(iv_myinfo_head);
            Bitmap bmp = BitmapFactory.decodeByteArray(bmpBytes, 0, bmpBytes.length);
            //bmp = ;
            showLoadingDialog();
            new CompressTask(bmp).execute();
            //civUserPhoto.setImageBitmap(bmp);
            //saveToFile(bmp);
        }

    }
    
    
    class CompressTask extends AsyncTask<Void , Void , Bitmap>{
        private Bitmap bmp;
        public CompressTask (Bitmap bmp){
            this.bmp = bmp;
        }
        
        @Override
        protected Bitmap doInBackground(Void... voids)
        {
            return BitmapUtil.comp(AccountInfoActivity.this , bmp);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            super.onPostExecute(bitmap);
            File file = new File(getCacheDir() + "/user.png");
            if(bitmap != null && file.exists()){
                uploadUserPhoto(file);
            }else{
                dismissLoadingDialog();
                showToastShort("图片写入失败！");
            }
        }
    }

    private void uploadUserPhoto(File file)
    {
        OkHttpUtils.getInstance().post().tag(this)
                .url(Constants.BASEURL + "index.php?g=app&m=user&a=avatar_upload")
                .addParams("userid" , CacheHelper.getUserId())
                .addFile("photo" ,"user.png", file)
                .build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {
                dismissLoadingDialog();
                showToastShort("上传头像失败");
            }

            @Override
            public void onResponse(String response, int id)
            {
                dismissLoadingDialog();
                URLResponse urlResponse =
                        GsonUtils.gsonToBean(response , URLResponse.class);
                if(urlResponse.getStatus() != 1){
                    showToastShort(urlResponse.getError());
                    return;
                }
                showToastShort("上传头像成功！");
                EventCenter center = new EventCenter(EventCode.CODE_UPDATE_PHOTO , urlResponse.getUrl());
                EventBus.getDefault().post(center);
                Glide.with(AccountInfoActivity.this).load(urlResponse.getUrl())
                        .into(civUserPhoto);
                UserResponse userResponse = CacheHelper.getUser();
                userResponse.setAvatar(urlResponse.getUrl());
                CacheHelper.saveCurrentUser(userResponse);
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
