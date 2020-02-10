package com.mobigod.data.repositories.schedule

import com.mobigod.data.mapper.FlightScheduleEntityMapper
import com.mobigod.data.models.schedules.ScheduleEntity
import com.mobigod.data.tester.StubGenerator
import com.mobigod.domain.usecases.schedule.FlightScheduleUseCase
import com.nhaarman.mockitokotlin2.any
import io.reactivex.Single
import konveyor.base.randomBuild
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by: Emmanuel Ozibo
 * //on: 09, 2020-02-09
 * //at: 18:39
 */

@RunWith(MockitoJUnitRunner::class)
class FlightScheduleRepositoryTest {

    lateinit var SUT: FlightScheduleRepository

    @Mock
    lateinit var remote: IFlightScheduleRemote

    private val mapper = FlightScheduleEntityMapper()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        SUT = FlightScheduleRepository(remote, mapper)
    }


    @Test
    fun `getFlightSchedules returns the list of domain schedules objects`() {
        val testData: FlightScheduleUseCase.Params = randomBuild()
        val randomTestData = StubGenerator.generateListOfScheduleEntities(5)
        stubRemoteResponse(randomTestData)
        val testObserver = SUT.getFlightSchedules(testData).test()

        testObserver.assertValue(randomTestData.map { mapper.mapFromEntity(it) })
    }



    @Test(expected = IllegalStateException::class)
    fun `getFlightSchedules throws an IllegalStateException error when called with a null params`() {
        val testData: FlightScheduleUseCase.Params? = null
        val randomTestData = StubGenerator.generateListOfScheduleEntities(5)
        stubRemoteResponse(randomTestData)
        val testObserver = SUT.getFlightSchedules(testData).test()

        testObserver.assertValue(randomTestData.map { mapper.mapFromEntity(it) })
    }



    @Test(expected = IllegalStateException::class)
    fun `getFlightSchedules throws an error when any of it's input(s) is empty`() {
        val testData = FlightScheduleUseCase.Params("LOS", "ABV", "")
        val randomTestData = StubGenerator.generateListOfScheduleEntities(5)
        stubRemoteResponse(randomTestData)
        val testObserver = SUT.getFlightSchedules(testData).test()

        testObserver.assertValue(randomTestData.map { mapper.mapFromEntity(it) })
    }


    private fun stubRemoteResponse(randomTestData: List<ScheduleEntity>) {
        `when`(remote.getFlightSchedules(any(), any(), any()))
            .thenReturn(Single.just(randomTestData))
    }

}