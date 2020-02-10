package com.mobigod.data.mapper

import com.mobigod.data.models.schedules.ScheduleEntity
import com.mobigod.data.tester.StubGenerator
import com.mobigod.domain.entities.flight.Schedule
import com.nhaarman.mockitokotlin2.doCallRealMethod
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by: Emmanuel Ozibo
 * //on: 08, 2020-02-08
 * //at: 10:39
 */
class FlightScheduleEntityMapperTest {

    lateinit var SUT: FlightScheduleEntityMapper

    @Before
    fun setUp() {
        SUT = FlightScheduleEntityMapper()
    }

    @Test
    fun `mapFromEntity maps Data module objects into Domain objects correctly`() {
        val testData = StubGenerator.generateScheduleEntity()
        val result = SUT.mapFromEntity(testData)
        checkForEquality(testData, result)
    }

    @Test
    fun `mapToEntity maps domain module objects into Data objects correctly`() {
        val testData = StubGenerator.generateSchedule()
        val result = SUT.mapToEntity(testData)
        checkForEquality(result, testData)
    }


    private fun checkForEquality(testData: ScheduleEntity, result: Schedule) {
        val flightEntityList = testData.FlightEntity
        val flightDomainList = result.flight

        assertThat(result.totalJourney.duration, `is`(testData.TotalJourneyEntity.Duration))

        for ((index, domainFlight) in flightDomainList.withIndex()){
            val entityFlight = flightEntityList[index]
            //test the flight codes
            assertThat(domainFlight.arrival.airportCode, `is`(entityFlight.ArrivalEntity.AirportCode))
            //test for dateTime equality
            assertThat(domainFlight.arrival.scheduledTimeLocal.dateTime, `is`(entityFlight.ArrivalEntity.ScheduledTimeLocalEntity.DateTime))

            assertThat(domainFlight.departure.airportCode, `is`(entityFlight.DepartureEntity.AirportCode))
            //test for dateTime equality
            assertThat(domainFlight.departure.scheduledTimeLocal.dateTime, `is`(entityFlight.DepartureEntity.scheduledTimeLocalEntity.DateTime))
        }

    }

}