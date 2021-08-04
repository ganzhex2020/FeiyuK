package com.phi.httplib.model

sealed class SealedResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : SealedResult<T>()
    data class Error(val exception: ResultException) : SealedResult<Nothing>()
}


