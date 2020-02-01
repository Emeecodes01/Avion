package com.mobigod.domain.stubs

import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.entities.flight.FlightSchedulesResponse
import com.mobigod.domain.usecases.airport.SaveAirportUseCase
import com.mobigod.domain.usecases.airport.SearchAirportUseCase
import com.mobigod.domain.usecases.schedule.FlightScheduleUseCase
import konveyor.base.randomBuild

object StubsGenerator {

    fun createFlightScheduleResponse(): FlightSchedulesResponse = randomBuild()
    fun createFlightScheduleParams(): FlightScheduleUseCase.Params = randomBuild()
    fun createAirportParam(): SearchAirportUseCase.Params = randomBuild()
    fun createAirport(): Airport = randomBuild()
    fun createSaveAirportParam(): SaveAirportUseCase.Param = randomBuild()


}