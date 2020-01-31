package com.mobigod.domain.stubs

import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.entities.flight.FlightSchedulesResponse
import com.mobigod.domain.usecases.AirportUseCase
import com.mobigod.domain.usecases.FlightScheduleUseCase
import konveyor.base.randomBuild

object StubsGenerator {

    fun createFlightScheduleResponse(): FlightSchedulesResponse = randomBuild()
    fun createFlightScheduleParams(): FlightScheduleUseCase.Params = randomBuild()
    fun createAirportParam(): AirportUseCase.Params = randomBuild()
    fun createAirport(): Airport = randomBuild()

}