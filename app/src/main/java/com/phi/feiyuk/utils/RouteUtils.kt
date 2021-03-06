package com.phi.feiyuk.utils

import android.content.Context
import android.content.Intent
import com.phi.feiyuk.ui.activity.LoginActivity
import com.phi.feiyuk.ui.activity.UserProfileActivity
import com.phi.httplib.provider.ClarityPotion

object RouteUtils {

    @JvmStatic
    fun go2Login(){
        val intent = Intent()
        intent.setClass(ClarityPotion.clarityPotion, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        ClarityPotion.clarityPotion.startActivity(intent)
    }

    @JvmStatic
    fun go2UserProfile(context: Context){
        val intent = Intent()
        intent.setClass(context, UserProfileActivity::class.java)
        context.startActivity(intent)
    }

}