package com.mobigod.data.repositories.auth

import com.mobigod.data.mapper.TokenMapper
import com.mobigod.domain.entities.auth.Token
import com.mobigod.domain.repository.IAuthRepository
import io.reactivex.Single
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 09:17*/
class AuthRepository @Inject constructor(val authCache: IAuthCache, val authRemote: IAuthRemote,
                                         val mapper: TokenMapper): IAuthRepository {


    override fun checkTokenExpired(): Boolean {
        return authCache.hasTokenExpired()
    }

    override fun authenticate(userKey: String, userSecret: String): Single<Token>{
        return authRemote.loginUser(userKey, userSecret)
            .doAfterSuccess {
                authCache.saveToken(it, System.currentTimeMillis())
                authCache.setUserAuthenticated()
            }
            .map {
                mapper.mapFromEntity(it)
            }
    }

}