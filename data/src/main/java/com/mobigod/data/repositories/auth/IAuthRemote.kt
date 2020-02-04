package com.mobigod.data.repositories.auth

import com.mobigod.data.models.auth.TokenEntity
import io.reactivex.Single

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 09:33*/
interface IAuthRemote {
    fun loginUser(userKey: String, userSecret: String): Single<TokenEntity>
}