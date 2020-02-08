package com.mobigod.domain.usecases.airport

import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import com.mobigod.domain.repository.IAirportsRepository
import com.mobigod.domain.usecases.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 08, 2020-02-08
//at: 17:46*/
class GetAirportsWithCodesUseCase @Inject constructor(private val repository: IAirportsRepository,
                                                      private val threadExecutor: ThreadExecutor,
                                                      private val postExecutionThread: PostExecutionThread):

    SingleUseCase<List<Airport>, GetAirportsWithCodesUseCase.Param>(threadExecutor, postExecutionThread){



    override fun buildUseCaseObservable(param: Param?): Single<List<Airport>> {
        checkNotNull(param){"Input a value"}
        assert(param.codes.isNotEmpty()){"You have entered an incorrect params"}
        return repository.getAirportsThatMatchesCodes(param.codes)
    }


    data class Param(
        val codes: List<String>
    )
}