package com.phi.basemodule.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phi.basemodule.callback.LoadMoreType
import com.phi.basemodule.callback.LoadState
import com.phi.basemodule.callback.LoadStateType
import com.phi.basemodule.callback.RefreshType
import com.phi.basemodule.ext.toast
import com.phi.basemodule.utils.LogUtils
import com.phi.httplib.model.handleException

import com.tencent.mmkv.MMKV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    val kv = MMKV.defaultMMKV()

    val loadState by lazy { MutableLiveData<LoadState>() }
    val dialogLoadingState by lazy { MutableLiveData<Boolean>() }
    val reFreshState by lazy { MutableLiveData<RefreshType>() }
    val loadMoreState by lazy { MutableLiveData<LoadMoreType>() }


    /**
     * 一般用于 同步 请求  并发多个请求 请单独更具自定义的业务写
     */
    fun launchUI(
        block: suspend CoroutineScope.() -> Unit,
        isShowToast: Boolean = true,
        isShowLoading: Boolean = false,
        isShowErrorPage: Boolean = false,
        isShowDiaLoading:Boolean = false,
        isWatchRefresh:Boolean = false,
        isWatchLoadMore:Boolean = false
    ) {
        viewModelScope.launch {
            //各种状态开始
            if (isShowLoading) {
                loadState.value = LoadState(LoadStateType.LOADING)
            }
            if (isShowDiaLoading){
                dialogLoadingState.value = true
            }
            if (isWatchRefresh){
                reFreshState.value = RefreshType.START
            }
            runCatching {
                block()
            }.onSuccess {
                if (isShowLoading||isShowErrorPage) {
                    loadState.value = LoadState(LoadStateType.SUCCESS)
                }
            }.onFailure {
                val exception = handleException(it)
                LogUtils.error("${exception.errCode}  ${exception.errorMsg}")
                if (isShowToast) {
                    toast(exception.errorMsg)
                }
                if (isShowLoading&&!isShowErrorPage){
                    loadState.value = LoadState(LoadStateType.SUCCESS)
                }
                if (isShowErrorPage) {
                    loadState.value = LoadState(LoadStateType.ERROR, exception.errorMsg)
                }
                if (isWatchLoadMore){
                    loadMoreState.value = LoadMoreType.FAIL
                }
            }
            //各种状态结束
            if (isShowDiaLoading){
                dialogLoadingState.value = false
            }
            if (isWatchRefresh){
                reFreshState.value = RefreshType.END
            }

        }
    }


}