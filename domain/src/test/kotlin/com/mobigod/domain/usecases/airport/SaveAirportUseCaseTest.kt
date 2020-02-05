package com.mobigod.domain.usecases.airport

import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import com.mobigod.domain.repository.IAirportsRepository
import com.mobigod.domain.stubs.StubsGenerator
import com.nhaarman.mockitokotlin2.any
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by: Emmanuel Ozibo
 * //on: 01, 2020-02-01
 * //at: 06:40
 */

@RunWith(MockitoJUnitRunner::class)
class SaveAirportUseCaseTest {

    /**
     * SUT -> SYSTEM UNDER TEST
     */
    lateinit var SUT: SaveAirportUseCase

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Mock
    lateinit var executor: ThreadExecutor

    @Mock
    lateinit var repository: IAirportsRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        SUT = SaveAirportUseCase(repository, executor, postExecutionThread)
    }


    @Test
    fun `saveAirport completes when valid params are passed`() {
        //Arrange
        setUpFakeResponse(Completable.complete())
        val param = StubsGenerator.createSaveAirportParam()

        //Act
        val testObs = SUT.buildUseCaseObservable(param).test()

        //Assert
        testObs.assertComplete()
    }


    @Test(expected = IllegalStateException::class)
    fun `saveAirport throws an exception when called with null params`(){
        //Arrange
        setUpFakeResponse(Completable.complete())
        val param = null

        //Act
        val testObs = SUT.buildUseCaseObservable(param).test()

        //Assert
        testObs.assertComplete()
    }


    private fun setUpFakeResponse(completable: Completable){
        Mockito.`when`(repository.saveAirport(any()))
            .thenReturn(completable)
    }


    private fun setUpFakeExecption() {
        Mockito.`when`(repository.saveAirport(null))
            .thenThrow(NullPointerException("The params you passed in was wrong"))
    }
}