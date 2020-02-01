package com.mobigod.data.mapper

import com.mobigod.data.models.airport.AirportEntity
import com.mobigod.domain.entities.airport.Airport
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 00:30*/
class AirportMapper @Inject constructor(): BaseMapper<AirportEntity, Airport>{

    override fun mapFromEntity(entity: AirportEntity): Airport {
        return Airport(entity.carriers, entity.city, entity.code, entity.country, entity.directFlights, entity.elev, entity.email, entity.icao
        ,entity.lat, entity.lon, entity.name, entity.phone, entity.runwayLength, entity.state, entity.type, entity.tz, entity.url, entity.woeid)
    }

    override fun mapToEntity(domain: Airport): AirportEntity {
        return AirportEntity(domain.carriers, domain.city, domain.code, domain.country, domain.directFlights, domain.elev, domain.email, domain.icao
            ,domain.lat, domain.lon, domain.name, domain.phone, domain.runwayLength, domain.state, domain.type, domain.tz, domain.url, domain.woeid)
    }

}