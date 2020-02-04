package com.mobigod.data.repositories.auth

import com.mobigod.data.models.auth.TokenEntity

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 08:56*/
interface IAuthCache {
    fun hasTokenExpired(): Boolean
    fun saveToken(tokenEntity: TokenEntity, loggedInTimeMillis: Long)
    fun updateClientCredentials(clientId: String, clientSecret: String)
    fun getClientId(): String
    fun getClientSecret(): String
    fun getToken(): TokenEntity
}