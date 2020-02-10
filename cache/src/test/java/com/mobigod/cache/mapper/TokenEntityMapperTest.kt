package com.mobigod.cache.mapper

import com.mobigod.cache.models.TokenPrefEntity
import com.mobigod.data.models.auth.TokenEntity
import konveyor.base.randomBuild
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by: Emmanuel Ozibo
 * //on: 09, 2020-02-09
 * //at: 19:38
 */
class TokenEntityMapperTest {


    lateinit var SUT: TokenEntityMapper

    @Before
    fun setUp() {
        SUT = TokenEntityMapper()
    }

    @Test
    fun `mapFromDbEntity maps pref entity into data entity correctly`(){
        val testData: TokenPrefEntity = randomBuild()
        val result = SUT.mapFromDbEntity(testData)
        assertForError(result, testData)
    }


    @Test
    fun `mapToDbEntity maps data entity into pref entity correctly`(){
        val testData: TokenEntity = randomBuild()
        val result = SUT.mapToDbEntity(testData)
        assertForError(testData, result)
    }


    private fun assertForError(result: TokenEntity, testData: TokenPrefEntity) {
        assertThat(result.tokenType, `is`(testData.tokenType))
        assertThat(result.accessToken, `is`(testData.accessToken))
        assertThat(result.expiresIn, `is`(testData.expiresIn))
    }
}