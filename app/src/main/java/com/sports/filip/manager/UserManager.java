package com.sports.filip.manager;

import com.sports.filip.activity.callback.OnConnectRongIMCallBack;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * author:pengfei
 * date:2017/3/19
 * Email:15291967179@163.com
 */

public class UserManager
{
    private static UserManager mInstance;
    
    private UserManager(){}
    
    public static UserManager getInstance(){
        
        if (mInstance == null)
        {
            synchronized (UserManager.class)
            {
                if (mInstance == null)
                    mInstance = new UserManager();
            }
        }
        return mInstance;
    }
    
    public void signRongIM(String token , final OnConnectRongIMCallBack imCallBack){
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            @Override
            public void onTokenIncorrect() {
                imCallBack.onFailed();
            }
            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            @Override
            public void onSuccess(String userid) {
                imCallBack.onSuccess();
            }
            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                imCallBack.onFailed();
            }
        });
    
    }
    
}
