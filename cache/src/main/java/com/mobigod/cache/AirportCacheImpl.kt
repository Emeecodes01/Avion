package com.mobigod.cache

import com.mobigod.cache.db.AvionDatabase
import com.mobigod.cache.mapper.AirportEntityMapper
import com.mobigod.cache.models.AirportDBEntity
import com.mobigod.cache.preference.IPreferenceManager
import com.mobigod.data.models.airport.AirportEntity
import com.mobigod.data.repositories.airport.IAirportCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 19:22*/
class AirportCacheImpl @Inject constructor(val database: AvionDatabase,
                                           val preferenceMgr: IPreferenceManager,
                                           val mapper: AirportEntityMapper): IAirportCache {

    override fun searchForAirportWith(query: String): Single<List<AirportEntity>> {
        assert(query.isEmpty()){"Can't work with an empty query"}
        assert(query.length < 3){"Type in some more query to help the search"}

        return database.airportDao().searchForAirport(query)
            .map {
                it.map { mapper.mapFromDbEntity(it) }
            }
    }

    override fun getAllAirports(): Single<List<AirportEntity>> {
        throw UnsupportedOperationException("WHY WILL YOU Fu**ing need 77k+ airports :(")
    }

    override fun saveAirport(airportEntity: AirportEntity?): Completable {
        checkNotNull(airportEntity){"Why do you want to save a null object?"}
        return database.airportDao().saveAirport(mapper.mapToDbEntity(airportEntity))
    }

}