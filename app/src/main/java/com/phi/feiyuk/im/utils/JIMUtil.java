package com.phi.feiyuk.im.utils;

import android.text.TextUtils;

import com.phi.basemodule.utils.LogUtils;
import com.phi.feiyuk.config.Const;
import com.phi.httplib.provider.ClarityPotion;
import com.tencent.mmkv.MMKV;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.options.MessageSendingOptions;
import cn.jpush.im.api.BasicCallback;

public class JIMUtil {

    private static final String TAG = "极光IM";
    private static final String PWD_SUFFIX = "PUSH";//注册极光IM的时候，密码是用户id+"PUSH"这个常量构成的
    //前缀，当uid不够长的时候无法注册
    public static final String PREFIX = "";
    private Map<String, Long> mMap;
    //针对消息发送动作的控制选项
    private MessageSendingOptions mOptions;
    private SimpleDateFormat mSimpleDateFormat;
    private String mImageString;
    private String mVoiceString;
    private String mLocationString;
    private BasicCallback mSendCompleteCallback;
  //  private SendMsgResultCallback mSendMsgResultCallback;
    private BasicCallback mHasReadCallback;
    private Runnable mHasReadRunable;

    private static JIMUtil sInstance;

    public JIMUtil() {
        mMap = new HashMap<>();
        mOptions = new MessageSendingOptions();
        mOptions.setShowNotification(false);//设置针对本次消息发送，是否需要在消息接收方的通知栏上展示通知
        mSimpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        mImageString = "[图片]";
        mVoiceString = "[语音]";
        mLocationString = "[位置]";
    }

    public static JIMUtil getInstance() {
        if (sInstance == null) {
            synchronized (JIMUtil.class) {
                if (sInstance == null) {
                    sInstance = new JIMUtil();
                }
            }
        }
        return sInstance;
    }

    public void init() {
        //开启消息漫游
        JMessageClient.setDebugMode(true);
        JMessageClient.init(ClarityPotion.clarityPotion, true);
    }

    /**
     * 将app中用户的uid转成IM用户的uid
     */
    private String getImUid(String uid) {
        return PREFIX + uid;
    }

    /**
     * 将IM用户的uid转成app用户的uid
     */
    private String getAppUid(String from) {
        if (!TextUtils.isEmpty(from) && from.length() > PREFIX.length()) {
            return from.substring(PREFIX.length());
        }
        return "";
    }

    /**
     * 注册并登录极光IM
     * @param uid
     */
    private void registerAndLoginJMessage(final String uid) {
        JMessageClient.register(uid, uid + PWD_SUFFIX, new BasicCallback() {

            @Override
            public void gotResult(int code, String msg) {
                LogUtils.error("注册极光回调---gotResult--->code: " + code + " msg: " + msg);
                if (code == 0) {
                    LogUtils.error("极光IM注册成功");
                    loginImClient(uid);
                }
            }
        });
    }

    /**
     * 登录极光IM
     */
    public void loginImClient(String uid) {
//        if (MMKV.defaultMMKV().decodeBool(Const.IM_LOGIN)) {
//            LogUtils.e("极光IM已经登录了");
//            JMessageClient.registerEventReceiver(JimMessageUtil.this);
//            MMKV.defaultMMKV().encode(Const.IM_LOGIN,true);
//         //   refreshAllUnReadMsgCount();
//            return;
//        }
        final String imUid = getImUid(uid);
        JMessageClient.login(imUid, imUid + PWD_SUFFIX, new BasicCallback() {
            @Override
            public void gotResult(int code, String msg) {
                LogUtils.error("登录极光回调---gotResult--->code: " + code + " msg: " + msg);
                if (code == 801003) {//用户不存在
                    LogUtils.error("未注册，用户不存在");
                    registerAndLoginJMessage(imUid);
                } else if (code == 0) {
                    LogUtils.error("极光IM登录成功");
                    MMKV.defaultMMKV().encode(Const.IM_LOGIN,true);
                    JMessageClient.registerEventReceiver(JIMUtil.this);
              //      refreshAllUnReadMsgCount();
                }
            }
        });
    }

    /**
     * 登出极光IM
     */
    public void logoutImClient() {
        JMessageClient.unRegisterEventReceiver(JIMUtil.this);
        JMessageClient.logout();
        MMKV.defaultMMKV().encode(Const.IM_LOGIN,false);
        LogUtils.error( "极光IM登出");
    }




}
