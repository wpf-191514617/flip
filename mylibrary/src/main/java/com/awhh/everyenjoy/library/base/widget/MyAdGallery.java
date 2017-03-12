package com.awhh.everyenjoy.library.base.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 无限滚动广告栏组件
 */
@SuppressWarnings("deprecation")
public class MyAdGallery extends Gallery implements
        AdapterView.OnItemClickListener,
        AdapterView.OnItemSelectedListener, OnTouchListener {
    /**
     * 显示的Activity
     */
    protected Context mContext;
    /**
     * 条目单击事件接口
     */
    protected MyOnItemClickListener mMyOnItemClickListener;
    /**
     * 图片切换时间
     */
    protected int mSwitchTime;
    /**
     * 自动滚动的定时器
     */
    protected Timer mTimer;
    /**
     * 圆点容器
     */
    protected LinearLayout mOvalLayout;
    /**
     * 当前选中的数组索引
     */
    protected int curIndex = 0;
    /**
     * 上次选中的数组索引
     */
    protected int oldIndex = 0;
    /**
     * 圆点选中时的背景ID
     */
    protected int mFocusedId;
    /**
     * 圆点正常时的背景ID
     */
    protected int mNormalId;
    /**
     * 图片资源ID组
     */
    protected int[] mAdsId;
    /**
     * 图片网络路径数组
     */
    protected List<String> mUris;
    /**
     * ImageView组
     */
    protected List<ImageView> listImgs;

//    private Drawable deDrawable;
    
    /**
     * 边距
     */
    protected float padding = 0.3f;
//    private ImageLoader imageLoader;
//    
//    private ImageLoaderHelper loaderHelper;

    public MyAdGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyAdGallery(Context context) {
        super(context);
    }

    public MyAdGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置底部圆点的左右边距
     *
     * @param padding 圆点的左右外边距占圆点窗口的百分比
     */
    public void setPadding(float padding) {

        this.padding = padding;

    }
    
    public void start(Context context, String[] mris, int[] adsId,
                      int switchTime, LinearLayout ovalLayout, int focusedId, int normalId , boolean isLoadNative) {
        
        List<String> mArrays = new ArrayList<String>();

        for (int i = 0; i < mris.length; i++) {

            mArrays.add(mris[i]);

        }

        start(context, mArrays, adsId,
                switchTime, ovalLayout, focusedId, normalId , isLoadNative);

    }

    /**
     * @param context    显示的Activity ,不能为null
     * @param mris       图片的网络路径数组 ,为空时 加载 adsId
     * @param adsId      图片组资源ID ,测试用
     * @param switchTime 图片切换时间 写0 为不自动切换
     * @param ovalLayout 圆点容器 ,可为空
     * @param focusedId  圆点选中时的背景ID,圆点容器可为空写0
     * @param normalId   圆点正常时的背景ID,圆点容器为空写0
     */
    public void start(Context context, List<String> mris, int[] adsId,
                      int switchTime, LinearLayout ovalLayout, int focusedId, int normalId , boolean isLoadNative) {
//        imageLoader = ImageLoader.getInstance();
//        loaderHelper = ImageLoaderHelper.getInstance(context);
//        deDrawable = context.getResources().getDrawable(R.drawable.default_img);
        this.mContext = context;
        this.mUris = mris;
        this.mAdsId = adsId;
        this.mSwitchTime = switchTime;
        this.mOvalLayout = ovalLayout;
        this.mFocusedId = focusedId;
        this.mNormalId = normalId;
        ininImages(isLoadNative);// 初始化图片组
        setAdapter(new AdAdapter());
        this.setOnItemClickListener(this);
        this.setOnTouchListener(this);
        this.setOnItemSelectedListener(this);
        this.setSoundEffectsEnabled(false);
        this.setAnimationDuration(700); // 动画时间
        this.setUnselectedAlpha(1); // 未选中项目的透明度
        // 不包含spacing会导致onKeyDown()失效!!! 失效onKeyDown()前先调用onScroll(null,1,0)可处理
        setSpacing(0);
        // 取靠近中间 图片数组的整倍数
        setSelection((getCount() / 2 / listImgs.size()) * listImgs.size()); // 默认选中中间位置为起始位置
        setFocusableInTouchMode(true);
        initOvalLayout();// 初始化圆点
        startTimer();// 开始自动滚动任务
    }

    /**
     * 初始化图片组
     */
    private void ininImages(boolean isLoadNative) {
        listImgs = new ArrayList<ImageView>(); // 图片组
        int len = mUris != null ? mUris.size() : mAdsId.length;
        for (int i = 0; i < len; i++) {
            ImageView imageview = new ImageView(mContext); // 实例化ImageView的对象
            imageview.setScaleType(ImageView.ScaleType.CENTER_CROP); // 设置缩放方式
            imageview.setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));

            if(isLoadNative){
                imageview.setImageResource(mAdsId[i]); // 为ImageView设置要显示的图片
            }else{
            if (mUris == null) {// 本地加载图片
                imageview.setImageResource(mAdsId[i]); // 为ImageView设置要显示的图片
            } else { // 网络加载图片
//                imageLoader.displayImage(mUris.get(i) , imageview , loaderHelper.getDisplayOptions());
                Glide.with(getContext()).load(mUris.get(i)).centerCrop()
                        .dontAnimate().into(new GlideDrawableImageViewTarget(imageview){
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                    }
                });
            }
            }
            listImgs.add(imageview);
        }

    }

    /**
     * 初始化圆点
     */
    private void initOvalLayout() {
        if (mOvalLayout != null && listImgs.size() < 2) {// 如果只有一第图时不显示圆点容器
            mOvalLayout.getLayoutParams().height = 0;
        } else if (mOvalLayout != null) {
                // 圆点的大小是 圆点窗口的 70%;
//            Ovalheight = 0;
//            if(isAgin) {
//                Ovalheight = (int) (mOvalLayout.getLayoutParams().height);
//            }else{
                int  Ovalheight = (int) (mOvalLayout.getLayoutParams().height *0.5);
//            }
            // 圆点的左右外边距是 圆点窗口的 20%;
                int Ovalmargin = (int) (mOvalLayout.getLayoutParams().height * padding);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        Ovalheight, Ovalheight);
                layoutParams.setMargins(Ovalmargin, 0, Ovalmargin, 0);

                if(mOvalLayout.getChildCount() > 0){
                    mOvalLayout.removeAllViews();
                }

                for (int i = 0; i < listImgs.size(); i++) {
                View v = new View(mContext); // 员点
                v.setLayoutParams(layoutParams);
                v.setBackgroundResource(mNormalId);
                mOvalLayout.addView(v);
            }
            // 选中第一个
            mOvalLayout.getChildAt(0).setBackgroundResource(mFocusedId);
        }
    }

    /**
     * 无限循环适配器
     */
    class AdAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (listImgs.size() < 2)// 如果只有一张图时不滚动
                return listImgs.size();
            return Integer.MAX_VALUE;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return listImgs.get(position % listImgs.size()); // 返回ImageView
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        int kEvent;
        if (isScrollingLeft(e1, e2)) { // 检查是否往左滑动
            kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
        } else { // 检查是否往右滑动
            kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
        }
        onKeyDown(kEvent, null);
        return true;

    }

    /**
     * 检查是否往左滑动
     */
    private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
        return e2.getX() > (e1.getX() + 50);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return super.onScroll(e1, e2, distanceX, distanceY);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (MotionEvent.ACTION_UP == event.getAction()
                || MotionEvent.ACTION_CANCEL == event.getAction()) {
            startTimer();// 开始自动滚动任务
        } else {
            stopTimer();// 停止自动滚动任务
        }
        return false;
    }

    /**
     * 图片切换事件
     */
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
                               long arg3) {

        if (onItemSelectedListener != null) {

            onItemSelectedListener.onSelectedItem(position);

        }

        curIndex = position % listImgs.size();
        if (mOvalLayout != null && listImgs.size() > 1) { // 切换圆点
            mOvalLayout.getChildAt(oldIndex).setBackgroundResource(mNormalId); // 圆点取消
            mOvalLayout.getChildAt(curIndex).setBackgroundResource(mFocusedId);// 圆点选中
            oldIndex = curIndex;
        }
    }

    private OnItemSelectedListener onItemSelectedListener;

    public void setOnSelectedListener(OnItemSelectedListener listener) {

        this.onItemSelectedListener = listener;

    }

    public interface OnItemSelectedListener {

        void onSelectedItem(int position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    /**
     * 项目点击事件
     */
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                            long arg3) {
        if (mMyOnItemClickListener != null) {
            mMyOnItemClickListener.onItemClick(curIndex);
        }
    }

    /**
     * 设置项目点击事件监听器
     */
    public void setMyOnItemClickListener(MyOnItemClickListener listener) {
        mMyOnItemClickListener = listener;
    }

    /**
     * 项目点击事件监听器接口
     */
    public interface MyOnItemClickListener {

        /**
         * @param curIndex //当前条目在数组中的下标
         */
        void onItemClick(int curIndex);

    }

    /**
     * 停止自动滚动任务
     */
    public void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    /**
     * 开始自动滚动任务 图片大于1张才滚动
     */
    public void startTimer() {
        if (mTimer == null && listImgs.size() > 1 && mSwitchTime > 0) {
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                public void run() {
                    handler.sendMessage(handler.obtainMessage(1));
                }
            }, mSwitchTime, mSwitchTime);
        }
    }

    /**
     * 处理定时滚动任务
     */
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 不包含spacing会导致onKeyDown()失效!!!
            // 失效onKeyDown()前先调用onScroll(null,1,0)可处理
            onScroll(null, null, 1, 0);
            onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
        }
    };
}
