package com.mobigod.remote.schedule

//import com.mobigod.data.models.airport.ScheduleEntity
import com.mobigod.data.models.schedules.ScheduleEntity
import com.mobigod.data.repositories.schedule.IFlightScheduleRemote
import com.mobigod.remote.ApiService
import io.reactivex.Single
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 08, 2020-02-08
//at: 00:03*/

class FlightScheduleRemoteImpl @Inject constructor(private val apiService: ApiService): IFlightScheduleRemote {


    override fun getFlightSchedules(origin: String, destination: String, departure: String): Single<List<ScheduleEntity>> {
        return apiService.getFlightSchedules(origin, destination, departure).map {
            it.scheduleResourceEntity.ScheduleEntity
        }
    }


}