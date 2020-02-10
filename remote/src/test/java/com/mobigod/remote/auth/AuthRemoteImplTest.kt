package com.mobigod.remote.auth

import com.mobigod.data.models.auth.TokenEntity
import com.mobigod.remote.ApiService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import konveyor.base.randomBuild
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by: Emmanuel Ozibo
 * //on: 09, 2020-02-09
 * //at: 19:16
 */

@RunWith(MockitoJUnitRunner::class)
class AuthRemoteImplTest {

    lateinit var SUT: AuthRemoteImpl

    @Mock
    lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        SUT = AuthRemoteImpl(apiService)
    }


    @Test
    fun `loginUser logs in successfully and return a token when called with correct parameter`(){
        val stubResponse: TokenEntity = randomBuild()
        stubResponse(stubResponse)
        val testObserver = SUT.loginUser("sd", "sd")
            .test()
        testObserver.assertValue(stubResponse)
    }


    private fun stubResponse(tokenEntity: TokenEntity) {
        whenever(apiService.loginUser(any(), any(), any()))
            .thenReturn(Single.just(tokenEntity))
    }
}