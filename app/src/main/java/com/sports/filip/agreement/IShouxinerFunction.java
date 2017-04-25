package com.sports.filip.agreement;

/**
 * Created by pengfei on 2016/6/28.
 */
public interface IShouxinerFunction extends IWebViewContainer{

//    public static final String FUNCTION_SUBMIT_PAY_ORDER = "submitPayOrder";

    public static final String FUNCTION_SUBMIT_PAY_ORDER = "payByPingxx";
    
    String FUNCTION_CHAT_LOGIN = "login";
    
    String FUNCTION_RECINDEX = "recindex";

    String FUNCTION_RECPOSTS = "recposts";
    
    String FUNCTION_RECEXPERT = "recexpert";
    
    String FUNCTION_MATCHCONTENT = "matchcontent";
    
    String FUNCTION_MATCHLIVE = "matchlive";
    
    String FUNCTION_SUBSCRIBE = "subscribe";
    
    String RECINDEX_TYPE8 = "recindex_type8";

    String FUNCTION_MATCHINDEX = "matchindex";
    
    String FUNCTION_USERINFO = "userinfo";

    String FUNCTION_POSTSADD = "postsadd";
    
    String FUNCTION_CLOSE = "close";

    String FUNCTION_SETTITLEBARVISIBLE = "setTitleBarVisible";

    String FUNCTION_OPEN = "open";

    String FUNCTION_MARCH_LEFT = "march_left";

    String FUNCTION_MARCH_RIGHT = "march_right";

    String FUNCTION_MARCH_FOLLOW = "march_follow";

    String FUNCTION_CLOSEWIN = "closeWin";

    String FUNCTION_RULE = "rule";
    
    String FUNCTION_MYBETS ="mybets";
    
    String FUNCTION_RECINDEXRECOMMAND = "recIndexRecommand";
    
    String EXPERT_ARTICLES = "expert_articles";
    
    void onExpert_articles(String param);
    
    void recIndexRecommand(String type);
    
    void onMyBets();
    
    void onDetailRule();
    
    void onChatLogin();
    
    void onCloseWin(String id, String type);

    void onMarch_left(String param);

    void onMarch_right(String param);
    
    void onFunctionUserinfo(String userid);
    
    void onMatchcontent(String ids);
    
    void onMatchlive(String href);


    /**
     * 提交支付订单
     */
   // public void submitPayOrder(String cmdSeq, int channelId, Map<String, String> channelParams , IAsyncComplete<FunctionResult> complete);

    public void onExecute(String type, IAsyncComplete<FunctionResult> callback);
    
    public void submitPayOrder(String charge, final IAsyncComplete<FunctionResult> callback);

    /** 在新的窗口中打开一个 URI */
    public void open(String cmdSeq, String uri, IAsyncComplete<FunctionResult> callback);

    public void OnOpenExperts(String expertId);

    public void onExpertsDetail(String userId, String id);
    
    void setFunctionSubscribe(String params);
    
    void recindex_type8();
    
    void onMatchindex(String params);

    void onMarch_follow();
    
    /**
     * 执行JS代码
     *
     * @param jsStr
     *            JS代码字符串，例如：alert('ok');
     */
    public void execJavascript(String jsStr);
    
}
