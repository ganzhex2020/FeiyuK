package com.phi.httplib.model

import com.google.gson.annotations.SerializedName

class BaseResult<out T>(
    val data: OutAny<T>,
    val msg: String,
    val ret: Int
)

data class OutAny<out T>(
    val code: Int,
    @SerializedName("msg")
    val message: String,
    @SerializedName("info")
    val datas: T
)

