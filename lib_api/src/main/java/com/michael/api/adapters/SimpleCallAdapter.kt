package com.michael.api.adapters

import com.michael.api.responses.BaseResponse
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class SimpleCallAdapter<R>(private val responseType: Type): CallAdapter<BaseResponse<R>, Any> {
    override fun responseType(): Type = responseType
    override fun adapt(call: Call<BaseResponse<R>>): Any = SimpleCall(call)
}