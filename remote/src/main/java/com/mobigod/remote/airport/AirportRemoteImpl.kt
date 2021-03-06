package com.mobigod.remote.airport

import com.mobigod.data.mapper.AirportMapper
import com.mobigod.data.models.airport.AirportEntity
import com.mobigod.data.repositories.airport.IAirportRemote
import com.mobigod.remote.ApiService
import io.reactivex.Single
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 10:06*/
class AirportRemoteImpl @Inject constructor(val apiService: ApiService, val airportService: AirportService, mapper: AirportMapper): IAirportRemote {

    override fun getAirports(): List<AirportEntity> {
        return airportService.getAirports("airports.json")
    }


}