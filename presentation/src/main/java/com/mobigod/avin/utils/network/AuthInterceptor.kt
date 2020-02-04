package com.mobigod.avin.utils.network

import android.util.Log
import com.google.gson.Gson
import com.mobigod.data.models.auth.TokenEntity
import com.mobigod.data.repositories.auth.IAuthCache
import com.mobigod.remote.ApiService
import okhttp3.*
import javax.inject.Inject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 23:25*/
class AuthInterceptor @Inject constructor(val authCache: IAuthCache): Interceptor {
    private val TAG = ""

    override fun intercept(chain: Interceptor.Chain): Response {

        if (authCache.hasTokenExpired()) {
            val clientId = authCache.getClientId()
            val clientSecret = authCache.getClientSecret()


            val client = okHttpClient()


            val httpUrl = HttpUrlFactory.buildHttpUrl("api.lufthansa.com", listOf("v1", "oauth", "token"),
                mapOf(
                    "client_id" to clientId,
                    "client_secret" to clientSecret,
                    "grant_type" to "client_credentials")
            )

            Log.i(TAG, "**** $httpUrl ****")

            val request = Request.Builder()
                .url(httpUrl)
                .build()

            val response = client.newCall(request).execute()
            val jsonString = response.body!!.string()

            val token: TokenEntity = Gson().fromJson(jsonString, TokenEntity::class.java)

            authCache.saveToken(token, System.currentTimeMillis())
        }

        val token = authCache.getToken()
        val newRequestChain = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${token.accessToken}")
            .build()

        return chain.proceed(newRequestChain)
    }


    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(logger = HttpLoggingInterceptor.Logger.DEFAULT).apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }).build()
    }


}