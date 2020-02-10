package com.mobigod.domain.usecases.auth

import com.mobigod.domain.entities.auth.Token
import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import com.mobigod.domain.repository.IAuthRepository
import com.mobigod.domain.usecases.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 08:29*/

open class LoginUseCase @Inject constructor(internal val authRepository: IAuthRepository,
    internal val executor: ThreadExecutor, internal val postExecutionThread: PostExecutionThread):
    SingleUseCase<Token, LoginUseCase.Params>(executor, postExecutionThread) {


    override fun buildUseCaseObservable(param: Params?): Single<Token> {
        checkNotNull(param)
        assert(param.clientId.isNotEmpty()){"You must provide User Key"}
        assert(param.clientSecret.isNotEmpty()){"You must provide User Secret"}
        return authRepository.authenticate(param.clientId, param.clientSecret)
    }


    data class Params(val clientId: String, val clientSecret: String)
}