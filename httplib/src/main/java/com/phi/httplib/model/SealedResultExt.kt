package com.phi.httplib.model

import com.phi.httplib.config.Constant.HTTP_SUCCESS_CODE

inline fun <T : Any> SealedResult<T>.checkSuccess(success: (T) -> Unit) {
    if (this is SealedResult.Success) {
        success(data)
    }
}

inline fun <T:Any> SealedResult<T>.checkError(error: (ResultException) -> Unit) {
    if (this is SealedResult.Error) {
        error(exception)
    }
}

inline fun <T:Any> BaseResult<T>.checkSuccess(success: (T) -> Unit){
    if (this.data.code == HTTP_SUCCESS_CODE){
        success(data.datas)
    }
    //登录失效回调 因为这垃圾后台处理的state是200 不能在异常中处理 只能在这里处理
    if (this.data.code==700){
        AuthonService.getInstance().onListener.setCode(700)
    }
}

inline fun <T:Any> BaseResult<T>.checkError(error: (ResultException) -> Unit) {
    if (this.data.code != HTTP_SUCCESS_CODE) {
        error(ResultException(data.code,data.message))
    }
}

