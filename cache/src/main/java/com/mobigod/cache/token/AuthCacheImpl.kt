package com.mobigod.cache.token

import com.mobigod.cache.mapper.TokenEntityMapper
import com.mobigod.cache.user.IUserManager
import com.mobigod.data.models.auth.TokenEntity
import com.mobigod.data.repositories.auth.IAuthCache
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 08:59*/


class AuthCacheImpl @Inject constructor(val tokenManager: ITokenManager,
                                        val userManager: IUserManager,
                                        val mapper: TokenEntityMapper): IAuthCache {



    override fun getClientId(): String = userManager.getUserClientId()
    override fun getClientSecret() = userManager.getUserClientSecret()

    override fun updateClientCredentials(clientId: String, clientSecret: String) {
        userManager.saveUserClientId(clientId)
        userManager.saveUserClientSecret(clientSecret)
    }


    override fun hasTokenExpired(): Boolean {
        return tokenManager.hasTokenExpired()
    }


    override fun saveToken(tokenEntity: TokenEntity, loggedInTimeMillis: Long) {
        val entity = mapper.mapToDbEntity(tokenEntity).copy(loggedInTime = loggedInTimeMillis)
        tokenManager.saveToken(entity)
    }


    override fun getToken(): TokenEntity {
        return mapper.mapFromDbEntity(tokenManager.getToken())
    }
}