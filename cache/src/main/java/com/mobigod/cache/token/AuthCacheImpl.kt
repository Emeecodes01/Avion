package com.mobigod.cache.token

import com.mobigod.cache.mapper.TokenEntityMapper
import com.mobigod.data.models.auth.TokenEntity
import com.mobigod.data.repositories.auth.IAuthCache
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 08:59*/


class AuthCacheImpl @Inject constructor(val tokenManager: ITokenManager,
                                        val mapper: TokenEntityMapper): IAuthCache {

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