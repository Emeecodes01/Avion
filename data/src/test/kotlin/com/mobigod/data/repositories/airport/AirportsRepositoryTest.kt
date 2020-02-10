package com.mobigod.data.repositories.airport

import com.mobigod.data.mapper.AirportMapper
import com.mobigod.data.models.airport.AirportEntity
import com.mobigod.data.tester.StubGenerator
import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.usecases.airport.SaveAirportUseCase
import com.mobigod.domain.usecases.airport.SearchAirportUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
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
 * //on: 03, 2020-02-03
 * //at: 11:28
 */
@RunWith(MockitoJUnitRunner::class)
class AirportsRepositoryTest {

    lateinit var SUT: AirportsRepository

    @Mock
    lateinit var remote: IAirportRemote

    @Mock
    lateinit var cache: IAirportCache

    private val mapper = AirportMapper()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        SUT = AirportsRepository(remote, cache, mapper)
    }

    @Test
    fun `getAirports returns a single list of airports`() {
        val listOfAirportEntities = StubGenerator.generateListOfAirportEntity(5)
        setUpStubRemoteResponse(listOfAirportEntities)
        val testObserver = SUT.getAirports().test()
        testObserver.assertValue(listOfAirportEntities.map { mapper.mapFromEntity(it) })
    }


    @Test
    fun `saveAirport executes correctly when the correct parameter is passed in`() {
        val  airport = StubGenerator.generateAirportParam()
        val completes = Completable.complete()
        setUpSaveAirportSubParam(completes)
        val testObserver = SUT.saveAirport(airport).test()
        testObserver.assertComplete()
    }


    @Test(expected = IllegalStateException::class)
    fun `saveAirport fails when a null parameter is passed in`(){
        val  airport = null
        val completes = Completable.complete()
        setUpSaveAirportSubParam(completes)
        val testObserver = SUT.saveAirport(airport).test()
        testObserver.assertComplete()
    }


    @Test
    fun `searchAirport returns all the list of airports when correct params is passed`() {
        val searchParam = SearchAirportUseCase.Params("lags")

        val airportList = StubGenerator.generateListOfAirportEntity(5)
        setUpSearchAirportStubResponse(airportList)
        val testObserver = SUT.searchAirport(searchParam).test()
        testObserver.assertValue(airportList.map { mapper.mapFromEntity(it) })
    }


    @Test(expected = IllegalStateException::class)
    fun `searchAirport fails when null params is passed`() {
        val searchParam = null
        val airportList = StubGenerator.generateListOfAirportEntity(5)
        setUpSearchAirportStubResponse(airportList)
        val testObserver = SUT.searchAirport(searchParam).test()
        testObserver.assertValue(airportList.map { mapper.mapFromEntity(it) })
    }


    @Test
    fun `getAirportsThatMatchesCodes returns a list of airport`() {
        val params = StubGenerator.generateListOfStrings(10)
        val stubResultData = StubGenerator.generateListOfAirportEntity(5)
        stubAirportThatMatchesCodeResult(stubResultData)

        val testObserver = SUT.getAirportsThatMatchesCodes(params).test()
        testObserver.assertValue(stubResultData.map { mapper.mapFromEntity(it) })
    }






    private fun stubAirportThatMatchesCodeResult(stubResultData: List<AirportEntity>) {
        Mockito.`when`(cache.getAirportsThatMatchesCodes(any()))
            .thenReturn(Single.just(stubResultData))
    }

    private fun setUpSearchAirportStubResponse(airportList: List<AirportEntity>) {
        Mockito.`when`(cache.searchForAirportWith(any()))
            .thenReturn(Single.just(airportList))
    }

    private fun setUpSaveAirportSubParam(completable: Completable) {
        Mockito.`when`(cache.saveAirport(any()))
            .thenReturn(completable)
    }

    private fun setUpStubRemoteResponse(airports: List<AirportEntity>) {
        Mockito.`when`(remote.getAirports())
            .thenReturn(airports)
    }

}