package com.mobigod.cache.token

import com.mobigod.cache.models.TokenPrefEntity

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 07:08*/

interface ITokenManager {
    fun hasTokenExpired(): Boolean
    fun saveToken(tokenPrefEntity: TokenPrefEntity)
    fun getToken(): TokenPrefEntity
}