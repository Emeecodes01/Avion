package com.mobigod.data.repositories.auth

import com.mobigod.data.mapper.TokenMapper
import com.mobigod.data.models.auth.TokenEntity
import io.reactivex.Single
import konveyor.base.randomBuild
import org.hamcrest.CoreMatchers
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by: Emmanuel Ozibo
 * //on: 09, 2020-02-09
 * //at: 18:01
 */

@RunWith(MockitoJUnitRunner::class)
class AuthRepositoryTest {

    lateinit var SUT: AuthRepository

    private val mapper = TokenMapper()

    @Mock
    lateinit var authCache: IAuthCache

    @Mock
    lateinit var authRemote: IAuthRemote

    private val CLIENT_ID = "user_id"
    private val CLIENT_SECRET = "user_secret"


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        SUT = AuthRepository(authCache, authRemote, mapper)
    }


    @Test
    fun checkTokenHasExpired() {
        setUpStub()
        val result = SUT.checkTokenExpired()
        assertThat(result, CoreMatchers.`is`(true))
    }


    @Test
    fun authenticateUser() {
        val tokenTestData: TokenEntity = randomBuild()
        val expected = mapper.mapFromEntity(tokenTestData)
        setUpAuthStubResponse(tokenTestData)
        val testObserver = SUT.authenticate(CLIENT_ID, CLIENT_SECRET)
            .test()

        testObserver.assertValue(expected)
    }


    private fun setUpAuthStubResponse(tokenEntity: TokenEntity) {
        `when`(authRemote.loginUser(anyString(), anyString()))
            .thenReturn(Single.just(tokenEntity))
    }

    private fun setUpStub() {
        `when`(authCache.hasTokenExpired())
            .thenReturn(true)
    }
}