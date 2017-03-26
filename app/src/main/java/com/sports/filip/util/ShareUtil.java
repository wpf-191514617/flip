package com.sports.filip.util;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.sports.filip.R;
import com.sports.filip.entity.ShareEntity;

/**
 * Author：huafang2016
 * Date: 2016/8/26 15:22
 * Email：15291967179@163.com
 */
public class ShareUtil
{

    private static ShareUtil instance = null;

    private ShareUtil() {
    }

    private Dialog shareDialog;

    private static Activity mContext;

    public static ShareUtil getInstance(Activity context) {
        if (null == instance) {
            instance = new ShareUtil();
        }
        mContext = context;
        return instance;
    }
    
    
    public void sharToPlatform(ShareEntity shareEntity) {
        shareDialog = new Dialog(mContext, R.style.dialogStyle);
        shareDialog.setContentView(R.layout.layout_share_view);
        View layoutWechat = shareDialog.findViewById(R.id.layoutWechat);
      //  layoutWechat.setOnClickListener(new OnShareClickListener(SHARE_MEDIA.WEIXIN, shareEntity));
        View layoutCircle = shareDialog.findViewById(R.id.layoutCircle);
       // layoutCircle.setOnClickListener(new OnShareClickListener(SHARE_MEDIA.WEIXIN_CIRCLE, shareEntity));
        View layoutQQ = shareDialog.findViewById(R.id.layoutQQ);
      //  layoutQQ.setOnClickListener(new OnShareClickListener(SHARE_MEDIA.QQ, shareEntity));
        View layoutQzone = shareDialog.findViewById(R.id.layoutQzone);
       // layoutQzone.setOnClickListener(new OnShareClickListener(SHARE_MEDIA.QZONE, shareEntity));
        View layoutWeibo = shareDialog.findViewById(R.id.layoutWeibo);
        //layoutWeibo.setOnClickListener(new OnShareClickListener(SHARE_MEDIA.SINA, shareEntity));
        shareDialog.findViewById(R.id.btnCancelShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shareDialog != null && shareDialog.isShowing()) {
                    shareDialog.dismiss();
                }
            }
        });
        Window window = shareDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.popuStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        shareDialog.setCanceledOnTouchOutside(true);
        shareDialog.show();
    }


   /* class OnShareClickListener implements View.OnClickListener {
        private SHARE_MEDIA media;
        private ShareEntity shareEntity;

        public OnShareClickListener(SHARE_MEDIA media, ShareEntity shareEntity) {
            this.media = media;
            this.shareEntity = shareEntity;
        }

        @Override
        public void onClick(View v) {

            if (shareDialog != null && shareDialog.isShowing()) {
                shareDialog.dismiss();
            }


            new ShareAction(mContext)
                    .withTitle(shareEntity.getTitle())
                    .withText(shareEntity.getContent())
                    //.withMedia(new UMImage(mContext, shareEntity.getImgUri()))
                    .withMedia(new UMImage(BaseAppManager.getInstance().getForwardActivity() , R.drawable.icon))
                    .withTargetUrl(shareEntity.getTargetUri())
                    .setPlatform(media)
                    .setCallback(new UMShareListener() {
                        @Override
                        public void onResult(SHARE_MEDIA platform) {
                            if (platform.name().equals("WEIXIN_FAVORITE")) {
                                Toast.makeText(mContext, platform + " 收藏成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, platform + " 分享成功", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(SHARE_MEDIA platform, Throwable t) {
                            Toast.makeText(mContext, platform + " 分享失败", Toast.LENGTH_SHORT).show();
                            if (t != null) {
                                Trace.d("throw", "throw:" + t.getMessage());
                            }
                        }

                        @Override
                        public void onCancel(SHARE_MEDIA platform) {
                            Toast.makeText(mContext, platform + " 分享取消", Toast.LENGTH_SHORT).show();
                        }
                    }).share();
        }
    }*/


}
