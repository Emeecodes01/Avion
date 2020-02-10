package com.mobigod.domain.usecases.airport

import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import com.mobigod.domain.repository.IAirportsRepository
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
import javax.inject.Inject

/**
 * Created by: Emmanuel Ozibo
 * //on: 09, 2020-02-09
 * //at: 16:20
 */

@RunWith(MockitoJUnitRunner::class)
class GetAirportsWithCodesUseCaseTest {

    lateinit var SUT: GetAirportsWithCodesUseCase

    @Mock
    lateinit var repository: IAirportsRepository

    @Mock
    lateinit var threadExecutor: ThreadExecutor


    @Mock
    lateinit var postExecutionThread: PostExecutionThread


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        SUT = GetAirportsWithCodesUseCase(repository, threadExecutor, postExecutionThread)
    }


    @Test
    fun `getAirportWithCodesUseCase returns data when valid input is passed`() {
        val airports = StubsGenerator.generateListOfAirports(5)
        setUpStubResponse(airports)

        val param = GetAirportsWithCodesUseCase.Param(listOf("LOS, ABV"))

        val testObserver = SUT.buildUseCaseObservable(param).test()

        testObserver.assertValue(airports)
    }

    @Test(expected = AssertionError::class)
    fun `getAirportWithCodesUseCase throws an error when an emptyList is passed`() {
        val airports = StubsGenerator.generateListOfAirports(5)
        setUpStubResponse(airports)

        val param = GetAirportsWithCodesUseCase.Param(listOf())

        val testObserver = SUT.buildUseCaseObservable(param).test()

        testObserver.assertValue(airports)
    }


    private fun setUpStubResponse(airports: List<Airport>) {
        `when`(repository.getAirportsThatMatchesCodes(any()))
            .thenReturn(Single.just(airports))
    }
}