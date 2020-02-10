package com.mobigod.avin.ui.flights

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobigod.avin.mapper.AirportViewMapper
import com.mobigod.avin.mapper.FlightScheduleViewMapper
import com.mobigod.avin.test.PStubGenerator
import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import com.mobigod.domain.repository.IAirportsRepository
import com.mobigod.domain.usecases.airport.GetAirportsWithCodesUseCase
import com.mobigod.domain.usecases.airport.SearchAirportUseCase
import com.mobigod.domain.usecases.schedule.FlightScheduleUseCase
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subscribers.DisposableSubscriber
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.runners.MockitoJUnitRunner

/**
 * Created by: Emmanuel Ozibo
 * //on: 08, 2020-02-08
 * //at: 03:35
 */
@RunWith(org.mockito.junit.MockitoJUnitRunner::class)
class FlightViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var SUT: FlightViewModel


    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Mock
    lateinit var threadExecutor: ThreadExecutor

    @Mock
    lateinit var repository: IAirportsRepository


    lateinit var searchAirportUseCase: SearchAirportUseCase

    @Mock
    lateinit var flightSearchAirportUseCase: FlightScheduleUseCase

    @Mock
    lateinit var getAirportsWithCodesUseCase: GetAirportsWithCodesUseCase

    private val mapper = AirportViewMapper()

    private val schedulesMapper = FlightScheduleViewMapper()

    @Captor
    private lateinit var captor: ArgumentCaptor<DisposableSingleObserver<List<Airport>>>
    private val TEST_QUERY = "Lago"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
//        searchAirportUseCase = mock()
//        flightSearchAirportUseCase = mock()
//        getAirportsWithCodesUseCase = mock()
        //captor = argumentCaptor()

        searchAirportUseCase = SearchAirportUseCase(repository, threadExecutor, postExecutionThread)

        val airports = PStubGenerator.getListOfAirports(10)
        setUpSearchAirportUseCase(airports)

        SUT = FlightViewModel(searchAirportUseCase, flightSearchAirportUseCase,
            getAirportsWithCodesUseCase, mapper, schedulesMapper)
    }

    @Test
    fun `searchAirports verify that execute is called`() {

        SUT.searchAirports(TEST_QUERY)

        verify(searchAirportUseCase, times(1))
            .execute(captor.capture(), eq(SearchAirportUseCase.Params(TEST_QUERY)))
    }



    private fun setUpSearchAirportUseCase(airports: List<Airport>) {
        Mockito.`when`(searchAirportUseCase.buildUseCaseObservable(any()))
            .thenReturn(Single.just(airports))
    }

}