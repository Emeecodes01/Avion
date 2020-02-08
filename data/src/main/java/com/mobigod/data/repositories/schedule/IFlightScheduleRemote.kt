package com.mobigod.data.repositories.schedule

import com.mobigod.data.models.schedules.ScheduleEntity
import io.reactivex.Single

/**Created by: Emmanuel Ozibo
//on: 08, 2020-02-08
//at: 00:00*/
interface IFlightScheduleRemote {
    fun getFlightSchedules(origin: String, destination: String, departure: String): Single<List<ScheduleEntity>>
}