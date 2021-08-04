package com.phi.feiyuk.config;

import android.content.Intent;


import com.phi.feiyuk.ui.activity.LoginActivity;
import com.phi.feiyuk.utils.RouteUtils;
import com.phi.httplib.model.AuthonService;
import com.phi.httplib.provider.ClarityPotion;
import com.tencent.mmkv.MMKV;


/**
 * Author:ganzhe
 * 时间:2021/4/15 20:16
 * 描述:This is AuthonManager
 */
public class AuthonManager {

    public static void authon(){
        AuthonService.getInstance().setOnListener(new AuthonService.OnListener() {
            @Override
            public void setCode(int code) {
                //修改登录状态
           //     MMKV.defaultMMKV().encode(KEY_LOGIN_STATE,false);
                RouteUtils.go2Login();
            }
        });
    }


}
