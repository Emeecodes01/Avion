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
//This is create in order to handle auth token refresh
class AuthInterceptor @Inject constructor(val authCache: IAuthCache): Interceptor {
    private val TAG = ""

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!authCache.isUserAuthenticated()){
            return chain.proceed(chain.request())
        }

//        if (!authCache.isUserAuthenticated() || !authCache.hasTokenExpired()) {
//            return chain.proceed(chain.request())//sha, just proceed with the request
//        }


        if (authCache.isUserAuthenticated() && authCache.hasTokenExpired()) {
            //You have to refresh token

            val clientId = authCache.getClientId()
            val clientSecret = authCache.getClientSecret()


            val client = okHttpClient()


            val httpUrl = HttpUrlFactory.buildHttpUrl("api.lufthansa.com", listOf("v1", "oauth", "token"),
                mapOf(
                    "client_id" to clientId,
                    "client_secret" to clientSecret,
                    "grant_type" to "client_credentials"))

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
            .addHeader("Content-Type", "application/json")
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