package com.mobigod.domain.usecases.auth

import com.mobigod.domain.entities.token.Token
import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import com.mobigod.domain.repository.IAuthRepository
import com.mobigod.domain.usecases.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 08:29*/

class LoginUseCase @Inject constructor(
    val authRepository: IAuthRepository,
    val executor: ThreadExecutor, val postExecutionThread: PostExecutionThread): SingleUseCase<Token, LoginUseCase.Params>(executor, postExecutionThread){


    override fun buildUseCaseObservable(param: Params?): Single<Token> {
        checkNotNull(param)
        assert(param.userKey.isNotEmpty()){"You must provide User Key"}
        assert(param.userSecret.isNotEmpty()){"You must provide User Secret"}
        return authRepository.authenticate(param.userKey, param.userSecret)
    }


    data class Params(val userKey: String, val userSecret: String)
}