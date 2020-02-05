package com.mobigod.avin.ui.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobigod.avin.mapper.TokenViewMapper
import com.mobigod.avin.models.auth.TokenModel
import com.mobigod.avin.states.Resource
import com.mobigod.domain.entities.auth.Token
import com.mobigod.domain.repository.IAuthRepository
import com.mobigod.domain.usecases.auth.LoginUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 16:47*/

class AuthViewModel @Inject constructor(val loginUseCase: LoginUseCase, val mapper: TokenViewMapper) : ViewModel() {

    private val subscribers = CompositeDisposable()

    val loginLiveData: MutableLiveData<Resource<TokenModel>> = MutableLiveData()


    fun loginUser(clientId: String, clientSecret: String) {
        loginLiveData.value = Resource.Loading()
        val loginParams = LoginUseCase.Params(clientId, clientSecret)
        loginUseCase.execute(LoginSingleSubscriber(), loginParams)
    }



    inner class LoginSingleSubscriber: DisposableSingleObserver<Token>(){
        override fun onSuccess(t: Token) {
            val receivedToken = mapper.mapToViewModel(t)
            loginLiveData.postValue(Resource.Success(receivedToken))
        }

        override fun onError(e: Throwable) {
            loginLiveData.postValue(Resource.Error(e.message))
        }
    }



    override fun onCleared() {
        super.onCleared()

        if (!subscribers.isDisposed)
            subscribers.clear()
    }


}