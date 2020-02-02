package com.mobigod.data.repositories.airport

import com.mobigod.data.mapper.AirportMapper
import com.mobigod.data.models.airport.AirportEntity
import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.repository.IAirportsRepository
import com.mobigod.domain.usecases.airport.SaveAirportUseCase
import com.mobigod.domain.usecases.airport.SearchAirportUseCase
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 05:29*/
class AirportsRepository @Inject constructor(val remote: IAirportRemote, val cache: IAirportCache, val mapper: AirportMapper): IAirportsRepository {

    override fun getAirports(): Single<List<Airport>> {
        return Single.just(remote.getAirports()
            .map {
                airportEntity ->
                mapper.mapFromEntity(airportEntity)
            })
    }


    override fun saveAirport(airport: SaveAirportUseCase.Param?): Completable {
        checkNotNull(airport){"You can't save a null airport"}
        val airportEntity = AirportEntity(
            airport.carriers, airport.city, airport.code, airport.country, airport.directFlights,
            airport.elev, airport.email, airport.icao, airport.lat, airport.lon, airport.name, airport.phone,
            airport.runwayLength, airport.state, airport.type, airport.tz, airport.url, airport.woeid)

        return cache.saveAirport(airportEntity)
    }


    override fun searchAirport(params: SearchAirportUseCase.Params?): Single<List<Airport>> {
        checkNotNull(params){"Query can't be null"}
        assert(params.query.isEmpty()){"To search you must enter a value"}

        return cache.searchForAirportWith(params.query)
            .map { airports -> airports.map { mapper.mapFromEntity(it) } }
    }

}