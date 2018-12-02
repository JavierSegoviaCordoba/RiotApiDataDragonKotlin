package com.videumcorp.datadragonwrapperkotlin.datadragon.utils

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.enqueue(callback: CallbackFun<T>.() -> Unit) {
    val callbackFun = CallbackFun<T>()
    callback.invoke(callbackFun)
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) callbackFun.onSuccess?.invoke(response.body())
            else callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            callbackFun.onFailure?.invoke(t)
        }
    })
}

fun <T> Call<T>.execute(callback: CallbackFun<T>.() -> Unit) {
    val callbackFun = CallbackFun<T>()
    callback.invoke(callbackFun)

    try {
        val response = this.execute()
        if (response.isSuccessful) callbackFun.onSuccess?.invoke(response.body())
        else callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
    } catch (e: Exception) {
        callbackFun.onFailure?.invoke(e)
    }
}

class CallbackFun<T> : RetrofitInterface<T> {
    var onSuccess: ((T?) -> Unit)? = null
    var onErrorCode: ((errorCode: ErrorCode) -> Unit)? = null
    var onFailure: ((t: Throwable) -> Unit)? = null

    override fun onSuccess(response: T) {
        onSuccess?.invoke(response)
    }

    override fun onErrorCode(errorCode: ErrorCode) {
        onErrorCode?.invoke(errorCode)
    }

    override fun onFailure(throwable: Throwable) {
        onFailure(throwable)
    }
}

interface RetrofitInterface<T> {

    fun onSuccess(response: T)

    fun onErrorCode(errorCode: ErrorCode)

    fun onFailure(throwable: Throwable)
}

data class ErrorCode(val code: Int, val message: String?)
