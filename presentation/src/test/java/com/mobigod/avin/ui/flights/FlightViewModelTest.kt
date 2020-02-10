package com.mobigod.avin.ui.flights

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobigod.avin.mapper.AirportViewMapper
import com.mobigod.avin.mapper.FlightScheduleViewMapper
import com.mobigod.avin.states.State
import com.mobigod.avin.test.PStubGenerator
import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.entities.flight.Schedule
import com.mobigod.domain.usecases.airport.GetAirportsWithCodesUseCase
import com.mobigod.domain.usecases.airport.SearchAirportUseCase
import com.mobigod.domain.usecases.schedule.FlightScheduleUseCase
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import net.bytebuddy.implementation.bytecode.member.HandleInvocation
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.lang.IllegalStateException


/**
 * Created by: Emmanuel Ozibo
 * //on: 08, 2020-02-08
 * //at: 03:35
 */
@RunWith(MockitoJUnitRunner::class)
class FlightViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    lateinit var SUT: FlightViewModel

    @Mock
    lateinit var searchAirportUseCase: SearchAirportUseCase

    @Mock
    lateinit var flightSearchAirportUseCase: FlightScheduleUseCase

    @Mock
    lateinit var getAirportsWithCodesUseCase: GetAirportsWithCodesUseCase


    private val mapper = AirportViewMapper()
    private val schedulesMapper = FlightScheduleViewMapper()
    private val TEST_QUERY = "Lago"


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val airports = PStubGenerator.getListOfAirports(10)
        //setUpSearchAirportUseCase(airports)

        SUT = FlightViewModel(searchAirportUseCase, flightSearchAirportUseCase,
            getAirportsWithCodesUseCase, mapper, schedulesMapper)
    }


    @Test
    fun `searchAirports verify that execute is called`() {
        SUT.searchAirports(TEST_QUERY)

        verify(searchAirportUseCase, times(1))
            .execute(any(), any())
    }


    @Test
    fun `searchAirports verify that it emits success state when successful`() {
        val airports = PStubGenerator.getListOfAirports(5)
        val query = "%Lago%"
        SUT.searchAirports(TEST_QUERY)

        argumentCaptor<DisposableSingleObserver<List<Airport>>>().apply {
            verify(searchAirportUseCase)
                .execute(capture(), eq(SearchAirportUseCase.Params(query)))
            firstValue.onSuccess(airports)

        }

        assertThat(SUT.airportsLiveData.value?.state, `is`(State.SUCCESS))
    }


    @Test
    fun `searchAirports verify that it emits failure state when error is thrown`() {
        val airports = PStubGenerator.getListOfAirports(5)
        val query = "%Lago%"
        SUT.searchAirports(TEST_QUERY)

        argumentCaptor<DisposableSingleObserver<List<Airport>>>().apply {
            verify(searchAirportUseCase)
                .execute(capture(), eq(SearchAirportUseCase.Params(query)))
            firstValue.onError(IllegalStateException())
        }

        assertThat(SUT.airportsLiveData.value?.state, `is`(State.ERROR))
    }


    @Test
    fun `searchAirports verify that it emits success, data is mapped correctly`() {
        val airports = PStubGenerator.getListOfAirports(5)
        val airportModels = airports.map { mapper.mapToViewModel(it) }

        SUT.searchAirports(TEST_QUERY)

        argumentCaptor<DisposableSingleObserver<List<Airport>>>().apply {
            verify(searchAirportUseCase)
                .execute(capture(), any())
            firstValue.onSuccess(airports)
        }

        assertThat(SUT.airportsLiveData.value?.data, `is`(airportModels))
    }


    @Test
    fun `getFlightSchedules calls execute`() {
        SUT.getFlightSchedules()

        verify(flightSearchAirportUseCase, times(1))
            .execute(any(), any())
    }

    @Test
    fun `getFlightSchedules emit loading state when called`() {
        SUT.getFlightSchedules()
        argumentCaptor<DisposableSingleObserver<List<Schedule>>>().apply {
            verify(flightSearchAirportUseCase, times(1))
                .execute(capture(), any())

            assertThat(SUT.flightSchedulesLiveData.value?.state, `is`(State.LOADING))
        }
    }


    @Test
    fun `getFlightSchedules emits success state when it executes successfully`() {
        SUT.getFlightSchedules()

        val airports = PStubGenerator.generateSchduleList(5)
        val argCaptor = argumentCaptor<DisposableSingleObserver<List<Schedule>>>()
        verify(flightSearchAirportUseCase).execute(argCaptor.capture(), any())
        argCaptor.firstValue.onSuccess(airports)

        assertThat(SUT.flightSchedulesLiveData.value?.state, `is`(State.SUCCESS))
    }


    private fun setUpSearchAirportUseCase(airports: List<Airport>) {
        Mockito.`when`(searchAirportUseCase.buildUseCaseObservable(eq(null)))
            .thenReturn(Single.just(airports))
    }

}