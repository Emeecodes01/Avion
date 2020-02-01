package com.mobigod.domain.usecases.airport

import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import com.mobigod.domain.repository.IAirportsRepository
import com.mobigod.domain.usecases.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 31, 2020-01-31
//at: 01:46*/


class SearchAirportUseCase @Inject constructor(private val airportRepository: IAirportsRepository,
                                               threadExecutor: ThreadExecutor,
                                               postExecutionThread: PostExecutionThread):
    SingleUseCase<Airport, SearchAirportUseCase.Params>(threadExecutor, postExecutionThread) {


    override fun buildUseCaseObservable(param: Params?): Single<Airport> {
        checkNotNull(param){"Params cannot be null"}
        assert(param.query.isNotEmpty()){"Query String cannot be empty"}
        return airportRepository.searchAirport(param)
    }

    data class Params(val query: String)
}