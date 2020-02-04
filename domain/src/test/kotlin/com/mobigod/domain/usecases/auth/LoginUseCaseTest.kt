package com.mobigod.domain.usecases.auth

import com.mobigod.domain.entities.token.Token
import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import com.mobigod.domain.repository.IAuthRepository
import com.mobigod.domain.stubs.StubsGenerator
import com.nhaarman.mockitokotlin2.any
import io.reactivex.Single
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.lang.AssertionError
import java.lang.IllegalStateException

/**
 * Created by: Emmanuel Ozibo
 * //on: 04, 2020-02-04
 * //at: 08:36
 */
@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseTest {

    lateinit var SUT: LoginUseCase

    @Mock
    lateinit var authRepository: IAuthRepository

    @Mock
    lateinit var executor: ThreadExecutor

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        SUT = LoginUseCase(authRepository, executor, postExecutionThread)
    }

    @Test(expected = IllegalStateException::class)
    fun `buildSingleUseCase throws an error when called with null params`(){
        val token = StubsGenerator.generateToken()
        setUpStubs(token)
        val testObserver = SUT.buildUseCaseObservable(null).test()
        testObserver.assertValue(token)
    }


    @Test(expected = AssertionError::class)
    fun `buildSingleUseCase throws an error when called with at least one empty values`(){
        val token = StubsGenerator.generateToken()
        val param = LoginUseCase.Params("", "secret01")
        setUpStubs(token)
        val testObserver = SUT.buildUseCaseObservable(param).test()
        testObserver.assertValue(token)
    }


    @Test
    fun `buildSingleUseCase returns token when called with correct params`(){
        val token = StubsGenerator.generateToken()
        val params = StubsGenerator.generateTokenParam()
        setUpStubs(token)
        val testObserver = SUT.buildUseCaseObservable(params).test()
        testObserver.assertValue(token)
    }



    private fun setUpStubs(token: Token) {
        Mockito.`when`(authRepository.authenticate(any(), any()))
            .thenReturn(Single.just(token))
    }
}