package com.mobigod.domain.repository

import com.mobigod.domain.entities.flight.FlightSchedulesResponse
import com.mobigod.domain.entities.flight.Schedule
import com.mobigod.domain.usecases.schedule.FlightScheduleUseCase
import io.reactivex.Single

interface IFlightSchedulesRepository {
    fun getFlightSchedules(params: FlightScheduleUseCase.Params?): Single<List<Schedule>>
}