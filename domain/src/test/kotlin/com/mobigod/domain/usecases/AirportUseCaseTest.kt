package com.mobigod.domain.usecases

import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import com.mobigod.domain.repository.ISearchAirports
import com.mobigod.domain.stubs.StubsGenerator
import com.nhaarman.mockitokotlin2.any
import io.reactivex.Single
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.lang.NullPointerException

/**
 * Created by: Emmanuel Ozibo
 * //on: 31, 2020-01-31
 * //at: 02:04
 */

@RunWith(MockitoJUnitRunner::class)
class AirportUseCaseTest {

    lateinit var SUT: AirportUseCase

    @Mock
    lateinit var threadExecutor: ThreadExecutor

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Mock
    lateinit var repository: ISearchAirports


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        SUT = AirportUseCase(repository, threadExecutor, postExecutionThread)
    }


    @Test
    fun `searchAirport returns data when called with correct params`() {
        //Arrange
        val param = StubsGenerator.createAirportParam()
        val response = StubsGenerator.createAirport()
        setUpStub(Single.just(response))


        //Act
        val testObserver = SUT.buildUseCaseObservable(param).test()

        //Assert
        testObserver.assertValue(response)
    }



    @Test(expected = IllegalStateException::class)
    fun `searchAirport throws error when called with a null params`() {
        //Arrange
        val param = null
        val response = StubsGenerator.createAirport()
        setUpStub(Single.just(response))

        //Act
        val testObserver = SUT.buildUseCaseObservable(param).test()

        //Assert
        testObserver.assertValue(response)
    }



    @Test(expected = AssertionError::class)
    fun `searchAirport throws error when called with an empty query`() {
        //Arrange
        val param = AirportUseCase.Params("")
        val response = StubsGenerator.createAirport()
        setUpStub(Single.just(response))

        //Act
        val testObserver = SUT.buildUseCaseObservable(param).test()

        //Assert
        testObserver.assertValue(response)
    }



    private fun setUpStub(value: Single<Airport>){
        `when`(repository.searchAirport(any()))
            .thenReturn(value)
    }

}