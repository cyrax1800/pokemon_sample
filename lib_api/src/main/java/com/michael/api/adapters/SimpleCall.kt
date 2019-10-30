package com.michael.api.adapters

import com.michael.api.responses.BaseResponse
import retrofit2.Call

typealias Simple<R> = SimpleCall<BaseResponse<R>>

class SimpleCall<R>(private val call: Call<BaseResponse<R>>) {

    fun then(onSuccess: (R) -> Unit): SimpleCallback<R> {
        // define callback
        val callback = SimpleCallback(onSuccess)

        // enqueue network call
        call.enqueue(callback)
        return callback
    }
}