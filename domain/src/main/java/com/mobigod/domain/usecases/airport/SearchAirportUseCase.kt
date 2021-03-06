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


open class SearchAirportUseCase @Inject constructor(val airportRepository: IAirportsRepository,
                                               threadExecutor: ThreadExecutor,
                                               postExecutionThread: PostExecutionThread):
    SingleUseCase<List<Airport>, SearchAirportUseCase.Params>(threadExecutor, postExecutionThread) {


    override fun buildUseCaseObservable(param: Params?): Single<List<Airport>> {
        checkNotNull(param){"Params cannot be null"}
        assert(param.query.isNotEmpty()){"Query String cannot be empty"}
        return airportRepository.searchAirport(param)
    }

    data class Params(val query: String)
}