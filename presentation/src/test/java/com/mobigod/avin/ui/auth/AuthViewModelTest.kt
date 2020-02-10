package com.mobigod.avin.ui.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobigod.avin.mapper.TokenViewMapper
import com.mobigod.avin.models.auth.TokenModel
import com.mobigod.avin.states.State
import com.mobigod.data.mapper.TokenMapper
import com.mobigod.domain.entities.auth.Token
import com.mobigod.domain.usecases.auth.LoginUseCase
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import konveyor.base.randomBuild
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by: Emmanuel Ozibo
 * //on: 10, 2020-02-10
 * //at: 11:18
 */

@RunWith(MockitoJUnitRunner::class)
class AuthViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var SUT: AuthViewModel

    @Mock
    lateinit var loginUseCase: LoginUseCase

    private val mapper = TokenViewMapper()

    private val CLIENT_ID = "client-id"
    private val CLIENT_SECRET = "client-secret"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val token: Token = randomBuild()
        //setUpStub(token)


        SUT = AuthViewModel(loginUseCase, mapper)
    }


    @Test
    fun `loginUser returns error state correctly, when execution fails`() {
        val loginParams = LoginUseCase.Params(CLIENT_ID, CLIENT_SECRET)

        SUT.loginUser(CLIENT_ID, CLIENT_SECRET)

        argumentCaptor<DisposableSingleObserver<Token>>().apply {
            verify(loginUseCase).execute(capture(), eq(loginParams))
            firstValue.onError(IllegalStateException())
        }

        assertThat(SUT.loginLiveData.value?.state, `is`(State.ERROR))
    }


    @Test
    fun `loginUser returns data and success state when called with the right params`() {
        val loginParams = LoginUseCase.Params(CLIENT_ID, CLIENT_SECRET)
        val token: Token = randomBuild()

        SUT.loginUser(CLIENT_ID, CLIENT_SECRET)

        argumentCaptor<DisposableSingleObserver<Token>>().apply {
            verify(loginUseCase).execute(capture(), eq(loginParams))
            firstValue.onSuccess(token)
        }

        assertThat(SUT.loginLiveData.value?.state, `is`(State.SUCCESS))
        assertThat(SUT.loginLiveData.value?.data, notNullValue())
    }


    @Test
    fun `loginUser executes gets called`() {
        SUT.loginUser(CLIENT_ID, CLIENT_SECRET)
        argumentCaptor<DisposableSingleObserver<Token>>().apply {
            verify(loginUseCase, times(1)).execute(capture(), any())
        }
    }

    @Test
    fun `loginUser correctly maps the data emitted on success`() {
        SUT.loginUser(CLIENT_ID, CLIENT_SECRET)
        val token: Token = randomBuild()
        val tokenModel = mapper.mapToViewModel(token)

        argumentCaptor<DisposableSingleObserver<Token>>().apply {
            verify(loginUseCase).execute(capture(), eq(LoginUseCase.Params(CLIENT_ID, CLIENT_SECRET)))
            firstValue.onSuccess(token)
        }

        assertDataForEquality(SUT.loginLiveData.value?.data, tokenModel)

    }


    @Test
    fun `loginUser shows the loading state when called`() {

        SUT.loginUser(CLIENT_ID, CLIENT_SECRET)
        argumentCaptor<DisposableSingleObserver<Token>>().apply {
            verify(loginUseCase, times(1)).execute(capture(), any())
        }
        assertThat(SUT.loginLiveData.value?.state, `is`(State.LOADING))
    }



    private fun assertDataForEquality(data: TokenModel?, tokenModel: TokenModel) {
        assertThat(data?.tokenType, `is`(tokenModel.tokenType))
        assertThat(data?.expiresIn, `is`(tokenModel.expiresIn))
        assertThat(data?.accessToken, `is`(tokenModel.accessToken))
    }


    private fun setUpStub(token: Token) {
        whenever(loginUseCase.buildUseCaseObservable(eq(null)))
            .thenReturn(Single.just(token))
    }


}