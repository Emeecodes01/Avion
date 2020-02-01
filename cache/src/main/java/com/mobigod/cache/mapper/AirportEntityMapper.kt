package com.mobigod.cache.mapper

import com.mobigod.cache.models.AirportDBEntity
import com.mobigod.data.models.airport.AirportEntity

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 21:04*/
class AirportEntityMapper: CoreMapper<AirportDBEntity, AirportEntity> {
    override fun mapFromDbEntity(value: AirportDBEntity): AirportEntity {
        return AirportEntity(value.carriers, value.city, value.code, value.country, value.directFlights, value.elev, value.email ,
            value.icao, value.lat, value.lon, value.name, value.phone,value.runwayLength, value.state, value.type, value.tz, value.url,
            value.woeid)
    }

    override fun mapToDbEntity(value: AirportEntity): AirportDBEntity {
        return AirportDBEntity(value.code, value.carriers, value.city, value.country, value.directFlights,
            value.elev, value.email, value.icao, value.lat, value.lon, value.name, value.phone, value.runwayLength,
            value.state, value.type, value.tz, value.url, value.woeid)
    }

}