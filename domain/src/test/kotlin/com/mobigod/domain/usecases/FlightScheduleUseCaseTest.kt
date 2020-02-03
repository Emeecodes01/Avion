package com.mobigod.domain.usecases

import com.mobigod.domain.entities.flight.FlightSchedulesResponse
import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import com.mobigod.domain.repository.IFlightSchedules
import com.mobigod.domain.stubs.StubsGenerator
import com.mobigod.domain.usecases.schedule.FlightScheduleUseCase
import com.nhaarman.mockitokotlin2.any
import io.reactivex.Single
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by: Emmanuel Ozibo
 * //on: 31, 2020-01-31
 * //at: 00:43
 */

@RunWith(MockitoJUnitRunner::class)
class FlightScheduleUseCaseTest {

    //SYSTEM UNDER TEST
    lateinit var SUT: FlightScheduleUseCase

    @Mock
    lateinit var flightsRepository: IFlightSchedules


    @Mock
    lateinit var threadExecutor: ThreadExecutor


    @Mock
    lateinit var postExecutionThread: PostExecutionThread


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        SUT = FlightScheduleUseCase(
            flightsRepository,
            threadExecutor,
            postExecutionThread
        )
    }



    @Test
    fun `getFlightSchedules returns data when valid input is passed`() {
        //Arrange
        val fakeResponse: FlightSchedulesResponse =  StubsGenerator.createFlightScheduleResponse()
        setUpFakeResponse(Single.just(fakeResponse))
        val fakeParams =  StubsGenerator.createFlightScheduleParams()

        //Act
        val test = SUT.buildUseCaseObservable(fakeParams).test()

        //Assert
        test.assertValue(fakeResponse)
    }


    @Test(expected = Exception::class)
    fun `getFlightSchedules throws an exception when a null value is passed in`() {
        //Arrange
        val fakeResponse: FlightSchedulesResponse =  StubsGenerator.createFlightScheduleResponse()
        setUpFakeResponse(Single.just(fakeResponse))
        val fakeParams =  null

        //Act
        val test = SUT.buildUseCaseObservable(fakeParams).test()

        //Assert
        test.assertValue(fakeResponse)
    }


    @Test(expected = AssertionError::class)
    fun `getFlightSchedules throws an exception when any of the field(s) is empty`() {
        //Arrange
        val fakeResponse: FlightSchedulesResponse =  StubsGenerator.createFlightScheduleResponse()
        setUpFakeResponse(Single.just(fakeResponse))
        val fakeParams =  FlightScheduleUseCase.Params("LOS", "BNI", "")

        //Act
        val test = SUT.buildUseCaseObservable(fakeParams).test()

        //Assert
        test.assertValue(fakeResponse)
    }



    private fun setUpFakeResponse(schedulesResponse: Single<FlightSchedulesResponse>) {
        `when`(flightsRepository.getFlightSchedules(any()))
            .thenReturn(schedulesResponse)
    }
}