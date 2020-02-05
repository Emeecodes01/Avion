package com.mobigod.remote.auth

import com.mobigod.data.models.auth.TokenEntity
import com.mobigod.data.repositories.auth.IAuthRemote
import com.mobigod.remote.ApiService
import io.reactivex.Single
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 09:35*/
class AuthRemoteImpl @Inject constructor(val apiService: ApiService): IAuthRemote {


    override fun loginUser(userKey: String, userSecret: String): Single<TokenEntity> {
        return apiService.loginUser(userKey, userSecret, "client_credentials")
    }

}