package com.michael.api.responses

import com.google.gson.JsonObject

class BaseResponse<T>(
    var data: T,
    var message: String,
    var error: ErrorResponse? = null,
    var meta: JsonObject
)