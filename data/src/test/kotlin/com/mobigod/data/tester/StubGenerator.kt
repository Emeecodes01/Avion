package com.mobigod.data.tester

import com.mobigod.data.models.airport.AirportEntity
import com.mobigod.data.models.schedules.FlightEntity
import com.mobigod.data.models.schedules.ScheduleEntity
import com.mobigod.data.models.schedules.TotalJourneyEntity
import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.entities.flight.Flight
import com.mobigod.domain.entities.flight.Schedule
import com.mobigod.domain.entities.flight.TotalJourney
import com.mobigod.domain.usecases.airport.SaveAirportUseCase
import com.mobigod.domain.usecases.airport.SearchAirportUseCase
import konveyor.base.randomBuild

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 12:40*/
object StubGenerator {

    fun createAirportEntity(): AirportEntity = randomBuild()
    fun generateAirportParam(): SaveAirportUseCase.Param = randomBuild()
    fun generateSearchAirportParam(): SearchAirportUseCase.Params = randomBuild()


    fun generateListOfAirportEntity(count: Int): List<AirportEntity> {
        val l: MutableList<AirportEntity> = mutableListOf()
        repeat(count){
            l.add(createAirportEntity())
        }
        return l
    }


    fun generateListOfFlightEntity(count: Int): List<FlightEntity> {
        val l: MutableList<FlightEntity> = mutableListOf()
        repeat(count){
            l.add(randomBuild())
        }
        return l
    }


    fun generateScheduleEntity(): ScheduleEntity {
        val flights = generateListOfFlightEntity(5)
        val totalJourneyEntity: TotalJourneyEntity = randomBuild()
        return ScheduleEntity(flights, totalJourneyEntity)
    }


    fun generateListOfScheduleEntities(number: Int): List<ScheduleEntity> {
        val list: MutableList<ScheduleEntity> = mutableListOf()
        repeat(number){
            list.add(generateScheduleEntity())
        }
        return list
    }

    fun generateSchedule(): Schedule {
        val flights = generateFlights(10)
        val totalJourney: TotalJourney = randomBuild()
        return Schedule(flights, totalJourney)
    }

    private fun generateFlights(count: Int): List<Flight> {
        val l: MutableList<Flight> = mutableListOf()
        repeat(count){
            l.add(randomBuild())
        }
        return l
    }


    fun generateListOfStrings(count: Int): List<String> {
        val l: MutableList<String> = mutableListOf()
        repeat(count){
            l.add(randomBuild())
        }
        return l
    }


    fun generateListOfAirports(count: Int): List<Airport> {
        val r: MutableList<Airport> = mutableListOf()
        repeat(count){
            r.add(randomBuild())
        }
        return r
    }

}