package com.phi.feiyuk

import android.content.Context
import cn.jpush.android.api.JPushInterface
import com.phi.basemodule.base.BaseApplication
import com.phi.feiyuk.config.AuthonManager
import com.phi.feiyuk.di.appModule
import com.phi.feiyuk.im.utils.JIMUtil
import com.phi.feiyuk.im.utils.JPushUtil
import com.phi.httplib.provider.ClarityPotion
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import kotlin.properties.Delegates


class MyApp :BaseApplication(){
    companion object {
        var CONTEXT: Context by Delegates.notNull()

    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        initKoin()
        AuthonManager.authon()
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.white, R.color.textColor) //全局设置主题颜色
            ClassicsHeader(context) //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        }
//        //设置全局的Footer构建器
//        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ -> //指定为经典Footer，默认是 BallPulseFooter
//            ClassicsFooter(context).setDrawableSize(20f)
//        }

        //初始化极光推送
        JPushUtil.getInstance().init(this)
        //极光im初始化
        JIMUtil.getInstance().init()

    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApp)
            androidFileProperties()
            modules(appModule)
        }
    }


}