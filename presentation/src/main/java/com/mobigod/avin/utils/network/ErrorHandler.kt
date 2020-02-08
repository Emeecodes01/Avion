package com.mobigod.avin.utils.network

import com.google.gson.Gson
import com.mobigod.avin.models.schedule.ApiErrorResponse
import retrofit2.HttpException

/**Created by: Emmanuel Ozibo
//on: 08, 2020-02-08
//at: 10:14*/
object ErrorHandler {

    fun getErrorMessage(error: Throwable): String? {
        var message = error.message
        if (error is HttpException){
            //resource not found
            val responseBody = error.response()?.errorBody()?.string()
            val gson = Gson()
            val apiErr: ApiErrorResponse = gson.fromJson(responseBody, ApiErrorResponse::class.java)
            message = apiErr.ProcessingErrors.ProcessingError.Description
        }

        return message
    }
}