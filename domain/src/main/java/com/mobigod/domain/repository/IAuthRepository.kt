package com.mobigod.domain.repository

import com.mobigod.domain.entities.auth.Token
import io.reactivex.Single

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 08:23*/
interface IAuthRepository {
    fun checkTokenExpired(): Boolean
    fun authenticate(userKey: String, userSecret: String): Single<Token>
}