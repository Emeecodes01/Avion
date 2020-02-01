package com.mobigod.data.repositories.airport

import com.mobigod.data.models.airport.AirportEntity
import io.reactivex.Completable

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 07:36*/
interface IAirportCache {
    fun saveAirport(airportEntity: AirportEntity?): Completable
}