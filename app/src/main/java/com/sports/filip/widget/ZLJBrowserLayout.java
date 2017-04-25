/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sports.filip.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.awhh.everyenjoy.library.base.util.CommonUtils;
import com.awhh.everyenjoy.library.base.util.StringUtils;
import com.awhh.everyenjoy.library.base.util.Trace;
import com.awhh.everyenjoy.library.base.widget.OnLoadUrlCallBackListener;
import com.sports.filip.activity.callback.OnCloseCallBack;
import com.sports.filip.agreement.FunctionResult;
import com.sports.filip.agreement.IAsyncComplete;
import com.sports.filip.agreement.IShouxinerFunction;
import com.sports.filip.agreement.IShouxinerParser;
import com.sports.filip.agreement.ShouxinerUtil;
import com.sports.filip.agreement.callback.OnPayCallBack;
import com.sports.filip.callback.OnAgreementCallBack;
import com.sports.filip.callback.OnChatLoginListener;
import com.sports.filip.callback.OnCloseWinListener;
import com.sports.filip.callback.OnDetailRuleAndBetCallBack;
import com.sports.filip.callback.OnExpertArticlesListener;
import com.sports.filip.callback.OnFunctionSubscribeListener;
import com.sports.filip.callback.OnFunctionUserCallBack;
import com.sports.filip.callback.OnMarchLeftCallBack;
import com.sports.filip.callback.OnMarchRightCallBack;
import com.sports.filip.callback.OnMarchfollowCallBack;
import com.sports.filip.callback.OnMatchContentCallBackListener;
import com.sports.filip.callback.OnMatchLiveCallBackListener;
import com.sports.filip.callback.OnMatchindexListener;
import com.sports.filip.callback.OnOpenExpertCallBackListener;
import com.sports.filip.callback.OnRecIndexRecommandListener;
import com.sports.filip.callback.OnRecindextypeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  Tau.Chen
 * Email:   1076559197@qq.com | tauchen1990@gmail.com
 * Date:    2015/3/31.
 * Description:
 */
public class ZLJBrowserLayout extends LinearLayout implements IShouxinerFunction
{

    private Context mContext = null;
    private WebView mWebView = null;
    private View mBrowserControllerView = null;
    private ImageButton mGoBackBtn = null;
    private ImageButton mGoForwardBtn = null;
    private ImageButton mGoBrowserBtn = null;
    private ImageButton mRefreshBtn = null;

    protected OnPayCallBack onPayCallBack;

    protected OnCloseCallBack onCloseCallBack;

    private OnLoadUrlCallBackListener onLoadUrlCallBackListener;

    protected OnAgreementCallBack onAgreementCallBack;

    protected OnOpenExpertCallBackListener onOpenExpertCallBackListener;

    public void setOnOpenExpertCallBackListener(OnOpenExpertCallBackListener onOpenExpertCallBackListener) {
        this.onOpenExpertCallBackListener = onOpenExpertCallBackListener;
    }

    public void setOnAgreementCallBack(OnAgreementCallBack onAgreementCallBack) {
        this.onAgreementCallBack = onAgreementCallBack;
    }

    public void setOnLoadUrlCallBackListener(OnLoadUrlCallBackListener onLoadUrlCallBackListener) {
        this.onLoadUrlCallBackListener = onLoadUrlCallBackListener;
    }

    //    protected OnOpenCallBackListener onOpenCallBackListener;

   /* public void setOnOpenCallBackListener(OnOpenCallBackListener onOpenCallBackListener) {
        this.onOpenCallBackListener = onOpenCallBackListener;
    }*/

    public void setOnCloseCallBack(OnCloseCallBack onCloseCallBack) {
        Trace.d("setclosecallback");
        this.onCloseCallBack = onCloseCallBack;
    }

    private IAsyncComplete<FunctionResult> callback;

    private List<String> mUrls;


    public void setOnPayCallBack(OnPayCallBack onPayCallBack) {
        this.onPayCallBack = onPayCallBack;
    }

    private int mBarHeight = 5;
    private ProgressBar mProgressBar = null;

    private String mLoadUrl;

    private String mWebTitle;

    public String getWebTitle() {
        return mWebTitle;
    }

    public ZLJBrowserLayout(Context context) {
        super(context);
        init(context);
    }

    public ZLJBrowserLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setOrientation(VERTICAL);
        mUrls = new ArrayList<>();
        mProgressBar = (ProgressBar) LayoutInflater.from(context).inflate(com.awhh.everyenjoy.library.R.layout.progress_horizontal, null);
        mProgressBar.setMax(100);
        mProgressBar.setProgress(0);
        addView(mProgressBar, LayoutParams.MATCH_PARENT, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, mBarHeight, getResources().getDisplayMetrics()));

        mWebView = new WebView(context);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.requestFocus();
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
//        mWebView.setScrollBarStyle(SCROLLBAR);
        mWebView.setHorizontalScrollBarEnabled(false);//水平不显示
        mWebView.setVerticalScrollBarEnabled(false); //垂直不显示

        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);

        LayoutParams lps = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
        addView(mWebView, lps);

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    if (onLoadUrlCallBackListener != null) {
                       onLoadUrlCallBackListener.onFinish(view.getUrl());
                    }
                    //mProgressBar.setVisibility(View.GONE);
                } else {
//                    mProgressBar.setVisibility(View.VISIBLE);
//                    mProgressBar.setProgress(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mWebTitle = title;
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mLoadUrl = url;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                mUrls.add(url);

                Trace.d("ZLJBrowserLayout url -> " + url);

                if (ShouxinerUtil.isShouxiner(url)) {

                    IShouxinerParser sp = IShouxinerParser.Factory
                            .newInstance();
                    sp.setFunctionImpl(ZLJBrowserLayout.this);
                    sp.execute(url);
                    return true;
                }

                view.loadUrl(url);

                return true;
            }
        });

        mBrowserControllerView = LayoutInflater.from(context).inflate(com.awhh.everyenjoy.library.R.layout.browser_controller, null);
        mGoBackBtn = (ImageButton) mBrowserControllerView.findViewById(com.awhh.everyenjoy.library.R.id.browser_controller_back);
        mGoForwardBtn = (ImageButton) mBrowserControllerView.findViewById(com.awhh.everyenjoy.library.R.id.browser_controller_forward);
        mGoBrowserBtn = (ImageButton) mBrowserControllerView.findViewById(com.awhh.everyenjoy.library.R.id.browser_controller_go);
        mRefreshBtn = (ImageButton) mBrowserControllerView.findViewById(com.awhh.everyenjoy.library.R.id.browser_controller_refresh);

        mGoBackBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (canGoBack()) {
                    goBack();
                }
            }
        });

        mGoForwardBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (canGoForward()) {
                    goForward();
                }
            }
        });

        mRefreshBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                loadUrl(mLoadUrl);
            }
        });

        mGoBrowserBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!CommonUtils.isEmpty(mLoadUrl)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(mLoadUrl));
                    mContext.startActivity(intent);
                }
            }
        });

        addView(mBrowserControllerView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    public void ToWeb(String url) {
        /*IShouxinerParser sp = IShouxinerParser.Factory
                .newInstance();
        sp.setFunctionImpl(ZLJBrowserLayout.this);
        sp.execute(url);*/

      /*  Bundle bundle = new Bundle();
        bundle.putString(ZLJWebActivity.BUNDLE_KEY_URL, url);
        Intent intent = new Intent(mContext, ZLJWebActivity.class);
        intent.putExtras(bundle);
        mContext.startActivity(intent);*/

    }

    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    public boolean canGoBack() {
        return null != mWebView ? mWebView.canGoBack() : false;
    }

    public boolean canGoForward() {
        return null != mWebView ? mWebView.canGoForward() : false;
    }

    public void goBack() {
        if (null != mWebView) {
            mWebView.goBack();
        }
    }

    public void goForward() {
        if (null != mWebView) {
            mWebView.goForward();
        }
    }

    public WebView getWebView() {
        return mWebView != null ? mWebView : null;
    }

    public void hideBrowserController() {
        mBrowserControllerView.setVisibility(View.GONE);
    }

    public void showBrowserController() {
        mBrowserControllerView.setVisibility(View.VISIBLE);
    }

//    @Override
//    public void submitPayOrder(String cmdSeq, int channelId, Map<String, String> channelParams, IAsyncComplete<FunctionResult> complete) {
//        Trace.d("支付 ： " + channelId);
//    }

    private OnMarchLeftCallBack onMarchLeftCallBack;

    private OnMarchRightCallBack onMarchRightCallBack;

    public void setOnMarchRightCallBack(OnMarchRightCallBack onMarchRightCallBack) {
        this.onMarchRightCallBack = onMarchRightCallBack;
    }

    public void setOnMarchLeftCallBack(OnMarchLeftCallBack onMarchLeftCallBack) {
        this.onMarchLeftCallBack = onMarchLeftCallBack;
    }

    private OnCloseWinListener onCloseWinListener;

    public void setOnCloseWinListener(OnCloseWinListener onCloseWinListener) {
        this.onCloseWinListener = onCloseWinListener;
    }

    
    private OnChatLoginListener mOnChatLoginListener;

    public void setOnChatLoginListener(OnChatLoginListener mOnChatLoginListener) {
        this.mOnChatLoginListener = mOnChatLoginListener;
    }

    
    private OnDetailRuleAndBetCallBack onDetailRuleAndBetCallBack;

    public void setOnDetailRuleAndBetCallBack(OnDetailRuleAndBetCallBack onDetailRuleAndBetCallBack) {
        this.onDetailRuleAndBetCallBack = onDetailRuleAndBetCallBack;
    }

    @Override
    public void onMyBets() {
        if(onDetailRuleAndBetCallBack != null)
            onDetailRuleAndBetCallBack.onMyBets();
    }

    @Override
    public void onDetailRule() {
        if(onDetailRuleAndBetCallBack != null)
            onDetailRuleAndBetCallBack.onRule();
    }

    private OnRecIndexRecommandListener onRecIndexRecommandListener;

    public void setOnRecIndexRecommandListener(OnRecIndexRecommandListener onRecIndexRecommandListener)
    {
        this.onRecIndexRecommandListener = onRecIndexRecommandListener;
    }

    private OnExpertArticlesListener onExpertArticlesListener;
    
    public void setOnExpertArticlesListener(OnExpertArticlesListener onExpertArticlesListener){
        this.onExpertArticlesListener = onExpertArticlesListener;
    }
    
    @Override
    public void onExpert_articles(String param)
    {
     if(onExpertArticlesListener != null){
         onExpertArticlesListener.onExpert_articles(param);
     }   
    }

    @Override
    public void recIndexRecommand(String type)
    {
        if(onRecIndexRecommandListener != null){
            onRecIndexRecommandListener.OnRecIndex(type);
        }
    }

    @Override
    public void onChatLogin() {
        if(mOnChatLoginListener != null)
            mOnChatLoginListener.onChatLogin();
    }

    @Override
    public void onCloseWin(String id , String type) {
        if(onCloseWinListener != null)
            onCloseWinListener.onCloseWin(id , type);
    }

    @Override
    public void onMarch_left(String param) {
        if(onMarchLeftCallBack != null){
            onMarchLeftCallBack.onMarch_left(param);
        }
    }

    @Override
    public void onMarch_right(String param) {
        if(onMarchRightCallBack != null){
            onMarchRightCallBack.onMarch_right(param);
        }
    }

    private OnFunctionUserCallBack onFunctionUserCallBack;

    public void setOnFunctionUserCallBack(OnFunctionUserCallBack onFunctionUserCallBack) {
        this.onFunctionUserCallBack = onFunctionUserCallBack;
    }

    @Override
    public void onFunctionUserinfo(String userid) {
        if(onFunctionUserCallBack != null){
            onFunctionUserCallBack.onFunctionUser(userid);
        }
    }

    private OnMatchContentCallBackListener onMatchContentCallBackListener;

    public void setOnMatchContentCallBackListener(OnMatchContentCallBackListener onMatchContentCallBackListener) {
        this.onMatchContentCallBackListener = onMatchContentCallBackListener;
    }

    @Override
    public void onMatchcontent(String ids) {
        if(null != onMatchContentCallBackListener){
            onMatchContentCallBackListener.onMatchContent(ids);
        }
    }
    
    private OnMatchLiveCallBackListener liveCallBackListener;

    public void setLiveCallBackListener(OnMatchLiveCallBackListener liveCallBackListener) {
        this.liveCallBackListener = liveCallBackListener;
    }

    @Override
    public void onMatchlive(String href) {
        if(liveCallBackListener != null){
            liveCallBackListener.onMatchLive(href);
        }
    }

    @Override
    public void onExecute(String type, IAsyncComplete<FunctionResult> callback) {
        if (onAgreementCallBack != null) {
            onAgreementCallBack.onExecute(type);
        }
    }

    @Override
    public void submitPayOrder(String charge, final IAsyncComplete<FunctionResult> callback) {
        this.callback = callback;
        if (onPayCallBack != null) {
            if (StringUtils.isEmpty(charge)) {
                onPayCallBack.onFailed();
                return;
            }
            onPayCallBack.onPay(charge);
        }
    }

    @Override
    public void open(String cmdSeq, String uri,
                     IAsyncComplete<FunctionResult> callback) {
        Trace.d("webFunction -> open");
//        loadUrl(uri);
        
        /*if(onOpenCallBackListener != null){
            onOpenCallBackListener.onOpen(uri);
        }*/

        callback.onComplete(new FunctionResult(0));
    }

    @Override
    public void OnOpenExperts(String expertId) {
        if (onOpenExpertCallBackListener != null) {
            onOpenExpertCallBackListener.onOpenExpert(expertId);
        }
    }

    @Override
    public void onExpertsDetail(String userId, String id) {
        if (onAgreementCallBack != null)
            onAgreementCallBack.openUserDetail(userId);
    }

    private OnFunctionSubscribeListener onFunctionSubscribeListener;

    public void setOnFunctionSubscribeListener(OnFunctionSubscribeListener onFunctionSubscribeListener) {
        this.onFunctionSubscribeListener = onFunctionSubscribeListener;
    }

    @Override
    public void setFunctionSubscribe(String params) {
        if(onFunctionSubscribeListener != null)
        {
            onFunctionSubscribeListener.onFunctionSubscribe(params);
        }
    }

    
    private OnRecindextypeListener onRecindextypeListener;

    public void setOnRecindextypeListener(OnRecindextypeListener onRecindextypeListener) {
        this.onRecindextypeListener = onRecindextypeListener;
    }

    @Override
    public void recindex_type8() {
        if(onRecindextypeListener != null)
            onRecindextypeListener.onOnRecindextype();
    }

    private OnMatchindexListener onMatchindexListener;

    public void setOnMatchindexListener(OnMatchindexListener onMatchindexListener) {
        this.onMatchindexListener = onMatchindexListener;
    }

    @Override
    public void onMatchindex(String params) {
        if(onMatchindexListener != null)
            onMatchindexListener.onMatchindex(params);
    }


    private OnMarchfollowCallBack marchfollowCallBack;

    public void setMarchfollowCallBack(OnMarchfollowCallBack marchfollowCallBack) {
        this.marchfollowCallBack = marchfollowCallBack;
    }

    @Override
    public void onMarch_follow() {
        if(marchfollowCallBack != null){
            marchfollowCallBack.onMarchfollow();
        }
    }

    @Override
    public void execJavascript(String jsStr) {
        if (null != ZLJBrowserLayout.this && null != jsStr) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    mWebView.evaluateJavascript("javascript:" + jsStr, null);
//                    evaluateJavascript(jsStr, null);
                } else {
                    loadUrl("javascript:" + jsStr);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    public void onPayCallBack(FunctionResult result) {
        if (callback != null)
            callback.onComplete(result);
    }

    @Override
    public void setTitleBarVisible(String cmdSeq, Boolean isVisible, IAsyncComplete<FunctionResult> callback) {

        Trace.d("webFunction -> visible");

        FunctionResult functionResult = new FunctionResult(0, "");
        callback.onComplete(functionResult);

        if (onCloseCallBack != null) {
            onCloseCallBack.OnTitleBarStatus(isVisible);
        }

    }

    @Override
    public void setTitleBarText(String cmdSeq, String title, IAsyncComplete<FunctionResult> callback) {

    }

    @Override
    public void close(String cmdSeq) {
        Trace.d("webFunction -> close");

        for (String str : mUrls) {

        }


//        if (mUrls.size() > 2) {
//            mUrls.remove(mUrls.get(mUrls.size() - 1));
//            mUrls.remove(mUrls.get(mUrls.size() - 1));
//            loadUrl(mUrls.get(mUrls.size() - 1));
//        } else {
        if (onCloseCallBack != null) {
            onCloseCallBack.OnClose();
            mUrls = null;
        } else {
            Trace.d("onclose-----------null");
        }
//        }

    }

    public void setChangedUrl() {

    }

}
