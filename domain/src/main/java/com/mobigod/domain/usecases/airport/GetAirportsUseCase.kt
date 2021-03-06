package com.mobigod.domain.usecases.airport

import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import com.mobigod.domain.repository.IAirportsRepository
import com.mobigod.domain.usecases.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 06:25*/

open class GetAirportsUseCase @Inject constructor(val repository: IAirportsRepository,
                                                  val threadExecutor: ThreadExecutor,
                                              val postExecutionThread: PostExecutionThread):
    SingleUseCase<List<Airport>, Unit>(threadExecutor, postExecutionThread){


    override fun buildUseCaseObservable(param: Unit?): Single<List<Airport>> {
        return repository.getAirports()
    }

}