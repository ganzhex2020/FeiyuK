package com.phi.basemodule.callback


import com.kingja.loadsir.callback.Callback
import com.phi.basemodule.R

/**
 *Author:ganzhe
 *时间:2020/10/23 19:49
 *描述:This is ErrorPageCallBack
 */
open class ErrorPageCallBack: Callback() {


    override fun onCreateView(): Int = R.layout.layout_error

}