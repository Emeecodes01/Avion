package com.mobigod.domain.repository

import com.mobigod.domain.entities.flight.FlightSchedulesResponse
import com.mobigod.domain.usecases.schedule.FlightScheduleUseCase
import io.reactivex.Single

interface IFlightSchedules {
    fun getFlightSchedules(params: FlightScheduleUseCase.Params?): Single<FlightSchedulesResponse>
}