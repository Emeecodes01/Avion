package com.mobigod.avin.mapper

import com.mobigod.avin.models.airport.AirportModel
import com.mobigod.domain.entities.airport.Airport

/**Created by: Emmanuel Ozibo
//on: 05, 2020-02-05
//at: 15:17*/
class AirportViewMapper: BaseViewMapper<AirportModel, Airport> {
    override fun mapFromViewModel(par: AirportModel): Airport {
        return  Airport(par.carriers, par.city, par.code, par.country, par.directFlights,
            par.elev, par.email, par.icao, par.lat, par.lon, par.name,
            par.phone, par.runwayLength, par.state, par.type, par.tz, par.url, par.woeid)
    }

    override fun mapToViewModel(par: Airport): AirportModel {
        return AirportModel(par.carriers, par.city, par.code, par.country, par.directFlights, par.elev, par.email,
            par.icao, par.lat, par.lon, par.name, par.phone, par.runwayLength, par.state,
            par.type, par.tz, par.url, par.woeid)
    }


}