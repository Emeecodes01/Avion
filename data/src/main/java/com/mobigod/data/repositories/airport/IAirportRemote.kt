package com.mobigod.data.repositories.airport

import com.mobigod.data.models.airport.AirportEntity
import com.mobigod.data.models.airport.ScheduleEntity
import com.mobigod.domain.entities.flight.FlightSchedulesResponse
import io.reactivex.Single

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 07:36*/
interface IAirportRemote {
    fun getFlightSchedules(origin: String, destination: String, departure: String): Single<List<ScheduleEntity>>
    fun getAirports(): List<AirportEntity>
}