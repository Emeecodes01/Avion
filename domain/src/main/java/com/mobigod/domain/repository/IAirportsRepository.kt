package com.mobigod.domain.repository

import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.usecases.airport.SaveAirportUseCase
import com.mobigod.domain.usecases.airport.SearchAirportUseCase
import io.reactivex.Completable
import io.reactivex.Single

/**Created by: Emmanuel Ozibo
//on: 31, 2020-01-31
//at: 01:47*/

interface IAirportsRepository {
    fun searchAirport(params: SearchAirportUseCase.Params?): Single<Airport>
    fun getAirports(): Single<List<Airport>>
    fun saveAirport(airport: SaveAirportUseCase.Param?): Completable
}