package com.mobigod.avin.ui.flights

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobigod.avin.mapper.AirportViewMapper
import com.mobigod.avin.mapper.FlightScheduleViewMapper
import com.mobigod.avin.test.PStubGenerator
import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.usecases.airport.GetAirportsWithCodesUseCase
import com.mobigod.domain.usecases.airport.SearchAirportUseCase
import com.mobigod.domain.usecases.schedule.FlightScheduleUseCase
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner

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
        setUpSearchAirportUseCase(airports)

        SUT = FlightViewModel(searchAirportUseCase, flightSearchAirportUseCase,
            getAirportsWithCodesUseCase, mapper, schedulesMapper)
    }


    @Test
    fun `searchAirports verify that execute is called`() {
        val airports = PStubGenerator.getListOfAirports(5)
        setUpSearchAirportUseCase(airports)

        SUT.searchAirports(TEST_QUERY)


        verify(searchAirportUseCase, times(1))
            .execute(any(), any())
    }



    private fun setUpSearchAirportUseCase(airports: List<Airport>) {
        Mockito.`when`(searchAirportUseCase.buildUseCaseObservable(eq(null)))
            .thenReturn(Single.just(airports))
    }

}