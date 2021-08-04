package com.phi.basemodule.base

import androidx.multidex.MultiDexApplication
import com.kingja.loadsir.core.LoadSir
import com.phi.basemodule.callback.EmptyPageCallBack
import com.phi.basemodule.callback.ErrorPageCallBack
import com.phi.basemodule.callback.LoadingCallBack
import com.phi.basemodule.utils.LogUtils


import com.tencent.mmkv.MMKV

open class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initMMKV()
        initLoadSir()
    }

    private fun initMMKV(){
        val rootDir = MMKV.initialize(this)
        LogUtils.error("mmkv root: $rootDir")
    }

    private fun initLoadSir() {
        LoadSir.beginBuilder()
            .addCallback(ErrorPageCallBack())
            .addCallback(LoadingCallBack())
            .addCallback(EmptyPageCallBack())
            .commit()
    }
}