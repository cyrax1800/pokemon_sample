package com.michael.api.adapters

import android.os.Handler
import android.os.Looper
import com.michael.api.responses.BaseResponse
import com.michael.api.responses.ErrorResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.android.asCoroutineDispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

internal val requestCompat = Handler(Looper.getMainLooper()).asCoroutineDispatcher("ApiRequest")

class SimpleCallback<R>(private val onSuccess: (R) -> Unit): Callback<BaseResponse<R>> {

    private var catch: ((ErrorResponse) -> Unit)? = null
    private var finally: (() -> Unit)? = null

    override fun onFailure(call: Call<BaseResponse<R>>, t: Throwable) {
        val errorResponse = if (t is HttpException) {
            ErrorResponse(t.code(), t.message())
        } else {
            ErrorResponse(0, t.message ?: "")
        }
        GlobalScope.launch(requestCompat) {
            catch?.invoke(errorResponse)
            finally?.invoke()
        }
    }

    override fun onResponse(call: Call<BaseResponse<R>>, r: Response<BaseResponse<R>>) {
        GlobalScope.launch(requestCompat) {
            handleResponse(r, onSuccess)
            finally?.invoke()
        }
    }

    private fun handleResponse(response: Response<BaseResponse<R>>, handler: (R) -> Unit) {

        if (response.isSuccessful) {
            val body = response.body()
            if (body is BaseResponse<*>) {
                body.error?.let { error ->
                    ErrorResponse(error.code, error.message)
                } ?: run {
                    handler.invoke(body as R)
                }
            } else {
                val errorResponse = ErrorResponse(0, "Empty Response")
                catch?.invoke(errorResponse)
            }
        } else {
            if (response.code() in 400..511) {
                response.body()?.let {
                    it.error?.let { error ->
                        ErrorResponse(error.code, error.message)
                    } ?: run {
                        handler.invoke(it as R)
                    }
                } ?: run {
                    val errorResponse = ErrorResponse(response.code(), response.message())
                    catch?.invoke(errorResponse)
                }
            }
        }
    }

    fun catch(block: (ErrorResponse) -> Unit): SimpleCallback<R> {
        return this.apply { catch = block }
    }

    fun finally(block: () -> Unit) {
        finally = block
    }
}