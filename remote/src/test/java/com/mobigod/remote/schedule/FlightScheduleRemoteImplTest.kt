package com.mobigod.remote.schedule

import com.mobigod.data.models.schedules.FlightScheduleResponse
import com.mobigod.data.models.schedules.ScheduleEntity
import com.mobigod.data.models.schedules.ScheduleResourceEntity
import com.mobigod.remote.ApiService
import com.mobigod.remote.test.StubGenerator
import com.nhaarman.mockitokotlin2.any
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
 * //on: 09, 2020-02-09
 * //at: 19:25
 */

@RunWith(MockitoJUnitRunner::class)
class FlightScheduleRemoteImplTest {

    lateinit var SUT: FlightScheduleRemoteImpl

    @Mock
    lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        SUT = FlightScheduleRemoteImpl(apiService)
    }

    @Test
    fun `getFlightSchedules returns list of schedule entities when called`(){
        val scheduleEntityList = StubGenerator.generateListOfScheduleEntities(6)
        stubResponseFromServer(scheduleEntityList)
        val testObserver = SUT.getFlightSchedules("LOS", "ABV", "3067-43-24").test()
        testObserver.assertValue(scheduleEntityList)
    }

    private fun stubResponseFromServer(scheduleEntityList: List<ScheduleEntity>) {
        Mockito.`when`(apiService.getFlightSchedules(any(), any(), any(), any()))
            .thenReturn(Single.just(FlightScheduleResponse(ScheduleResourceEntity(scheduleEntityList))))
    }
}