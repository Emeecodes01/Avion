package com.mobigod.cache

import com.mobigod.cache.db.AvionDatabase
import com.mobigod.cache.mapper.AirportEntityMapper
import com.mobigod.cache.mapper.CoreMapper
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
class AirportCacheImpl @Inject constructor(private val database: AvionDatabase,
                                           private val preferenceMgr: IPreferenceManager,
                                           private val mapper: CoreMapper<AirportDBEntity, AirportEntity>): IAirportCache {



    override fun getAirportWithCode(code: String): Single<AirportEntity> {
        return database.airportDao().getAirportByCode(code)
            .map { mapper.mapFromDbEntity(it) }
    }


    override fun getAirportsThatMatchesCodes(codes: List<String>): Single<List<AirportEntity>> {
        return database.airportDao().getAirportsWithCodes(codes)
            .map { dbAirports ->
                dbAirports.map { mapper.mapFromDbEntity(it) } }
    }


    override fun searchForAirportWith(query: String): Single<List<AirportEntity>> {
        assert(query.isNotEmpty()){"Can't work with an empty query"}
        assert(query.length >= 3){"Type in some more query to help the search"}

        return database.airportDao().searchForAirport(query)
            .map {
                it.map { mapper.mapFromDbEntity(it) }
            }
    }


    override fun getAllAirports(): Single<List<AirportEntity>> {
        throw UnsupportedOperationException("WHY WILL YOU Fu**ing need 77k+ airports :(")
    }

    override fun saveAirport(airportEntity: AirportEntity?): Completable{
        checkNotNull(airportEntity){"Why do you want to save a null object?"}
        return database.airportDao().saveAirport(mapper.mapToDbEntity(airportEntity))
    }

}