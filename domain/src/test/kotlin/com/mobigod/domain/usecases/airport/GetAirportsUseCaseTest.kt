package com.mobigod.domain.usecases.airport

import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import com.mobigod.domain.repository.IAirportsRepository
import com.mobigod.domain.stubs.StubsGenerator
import io.reactivex.Single
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by: Emmanuel Ozibo
 * //on: 01, 2020-02-01
 * //at: 06:40
 */

class GetAirportsUseCaseTest {

    /**
     * SUT -> SYSTEM UNDER TEST
     */
    lateinit var SUT: GetAirportsUseCase

    private val NUM_OF_AIRPORTS = 5

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Mock
    lateinit var executor: ThreadExecutor

    @Mock
    lateinit var repository: IAirportsRepository



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        SUT = GetAirportsUseCase(repository, executor, postExecutionThread)
    }


    @Test
    fun `getAirport returns a list of airports when called`() {
        //Arrange
        val airports = StubsGenerator.generateListOfAirports(NUM_OF_AIRPORTS)
        setUpFakeResponse(airports)

        //Act
        val testObserver = SUT.buildUseCaseObservable(Unit).test()

        //Assert
        testObserver.assertValue(airports)
    }



    private fun setUpFakeResponse(airports: List<Airport>) {
        Mockito.`when`(repository.getAirports())
            .thenReturn(Single.just(airports))
    }


}