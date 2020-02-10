package com.mobigod.data.repositories.schedule

import com.mobigod.data.mapper.FlightScheduleEntityMapper
import com.mobigod.domain.entities.flight.Schedule
import com.mobigod.domain.repository.IFlightSchedulesRepository
import com.mobigod.domain.usecases.schedule.FlightScheduleUseCase
import io.reactivex.Single
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 08, 2020-02-08
//at: 00:00*/
class FlightScheduleRepository @Inject constructor(val remote: IFlightScheduleRemote, val mapper: FlightScheduleEntityMapper): IFlightSchedulesRepository {


    override fun getFlightSchedules(params: FlightScheduleUseCase.Params?): Single<List<Schedule>> {
        checkNotNull(params){"You can't pass a null params"}
//        check(params.origin.isEmpty() && params.origin.isEmpty() && params.dateOfDeparture.isEmpty())
        //{"Your field parameters are not correct"}
        return remote.getFlightSchedules(params.origin, params.destination, params.dateOfDeparture)
            .map {
                it.map {
                    mapper.mapFromEntity(it)
                }
            }
    }

}