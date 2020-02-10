package com.mobigod.data.mapper

import com.mobigod.data.models.auth.TokenEntity
import com.mobigod.domain.entities.auth.Token
import konveyor.base.randomBuild
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by: Emmanuel Ozibo
 * //on: 09, 2020-02-09
 * //at: 17:36
 */
class TokenMapperTest {

    lateinit var SUT: TokenMapper

    @Before
    fun setUp() {
        SUT = TokenMapper()
    }


    @Test
    fun `mapFromEntity maps token entity into domain object correctly`() {
        val testData: TokenEntity = randomBuild()
        val result = SUT.mapFromEntity(testData)
        assertForEquality(testData, result)
    }

    @Test
    fun `mapToEntity maps domain object into entity object correctly`() {
        val testData: Token = randomBuild()
        val result = SUT.mapToEntity(testData)
        assertForEquality(result, testData)
    }


    private fun assertForEquality(testData: TokenEntity, result: Token) {
        assertThat(result.tokenType, `is`(testData.tokenType))
        assertThat(result.accessToken, `is`(testData.accessToken))
        assertThat(result.expiresIn, `is`(testData.expiresIn))
    }
}