package com.phi.feiyuk.im.utils;

import android.content.Context;


import com.phi.basemodule.utils.LogUtils;
import com.phi.httplib.provider.ClarityPotion;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by cxf on 2017/8/3.
 * 极光推送相关
 */

public class JPushUtil {

    public static final String TAG = "极光推送";
    private static JPushUtil sInstance;
    private boolean mClickNotification;
    private int mNotificationType;

    private JPushUtil() {

    }

    public static JPushUtil getInstance() {
        if (sInstance == null) {
            synchronized (JPushUtil.class) {
                if (sInstance == null) {
                    sInstance = new JPushUtil();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context) {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(context);
        LogUtils.error("regID------>" + JPushInterface.getRegistrationID(context));
    }


    public void logout() {
        stopPush();
    }

    public void resumePush() {
        if (JPushInterface.isPushStopped(ClarityPotion.healingSalve)) {
            JPushInterface.resumePush(ClarityPotion.healingSalve);
        }
    }

    public void stopPush() {
        JPushInterface.stopPush(ClarityPotion.healingSalve);
    }

    public boolean isClickNotification() {
        return mClickNotification;
    }

    public void setClickNotification(boolean clickNotification) {
        mClickNotification = clickNotification;
    }

    public int getNotificationType() {
        return mNotificationType;
    }

    public void setNotificationType(int notificationType) {
        mNotificationType = notificationType;
    }

    /**
     * 获取极光推送 RegistrationID
     */
    public String getPushID() {
        return JPushInterface.getRegistrationID(ClarityPotion.healingSalve);
    }
}
