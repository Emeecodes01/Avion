package com.mobigod.domain.stubs

import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.entities.flight.FlightSchedulesResponse
import com.mobigod.domain.entities.auth.Token
import com.mobigod.domain.entities.flight.Schedule
import com.mobigod.domain.usecases.airport.SaveAirportUseCase
import com.mobigod.domain.usecases.airport.SearchAirportUseCase
import com.mobigod.domain.usecases.auth.LoginUseCase
import com.mobigod.domain.usecases.schedule.FlightScheduleUseCase
import konveyor.base.randomBuild

object StubsGenerator {

    fun createFlightScheduleResponse(): FlightSchedulesResponse = randomBuild()
    fun createFlightScheduleParams(): FlightScheduleUseCase.Params = FlightScheduleUseCase.Params("JFK", "LOS", "2020-06-93")
    fun createAirportParam(): SearchAirportUseCase.Params = randomBuild()
    fun createAirport(): Airport = randomBuild()
    fun createSaveAirportParam(): SaveAirportUseCase.Param = randomBuild()
    fun generateTokenParam(): LoginUseCase.Params = randomBuild()
    fun generateToken(): Token = randomBuild()

    fun generateListOfAirports(number: Int): List<Airport> {
        val listOfAirport = mutableListOf<Airport>()
        for (index in 0..number) {
            listOfAirport.add(index, createAirport())
        }
        return listOfAirport
    }



    fun createSchduleStubs(): List<Schedule> {
        val schedules: MutableList<Schedule> = mutableListOf()
        repeat(5){
            schedules.add(randomBuild())
        }
        return schedules
    }


}