package com.mobigod.cache.token

import com.google.gson.Gson
import com.mobigod.cache.models.TokenPrefEntity
import com.mobigod.cache.preference.IPreferenceManager
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 07:24*/
class TokenManager @Inject constructor(val preferenceManager: IPreferenceManager): ITokenManager {


    override fun hasTokenExpired(): Boolean {
        val currentToken = getToken()
        val currentTime = System.currentTimeMillis()
        val lastTokenSavedTime = currentToken.loggedInTime

        val secs = (currentTime - lastTokenSavedTime) / 1000
        return secs > currentToken.expiresIn
    }

    override fun saveToken(tokenPrefEntity: TokenPrefEntity) {
        val tokenString = tokenPrefEntity.toString()
        preferenceManager.accessToken = tokenString
    }



    override fun getToken(): TokenPrefEntity {
        val gson = Gson()
        val token= gson.fromJson(preferenceManager.accessToken, TokenPrefEntity::class.java)
        return token
    }

}