package com.mobigod.domain.repository

import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.usecases.AirportUseCase
import io.reactivex.Single

/**Created by: Emmanuel Ozibo
//on: 31, 2020-01-31
//at: 01:47*/

interface ISearchAirports {
    fun searchAirport(params: AirportUseCase.Params?): Single<Airport>
}