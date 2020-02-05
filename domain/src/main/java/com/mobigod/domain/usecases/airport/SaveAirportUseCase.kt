package com.mobigod.domain.usecases.airport

import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import com.mobigod.domain.repository.IAirportsRepository
import com.mobigod.domain.usecases.base.CompletableUseCase
import com.mobigod.domain.usecases.base.SingleUseCase
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 06:26*/

class SaveAirportUseCase @Inject constructor(
    private val repository: IAirportsRepository,
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread): CompletableUseCase<SaveAirportUseCase.Param>(threadExecutor, postExecutionThread) {




    override fun buildUseCaseObservable(param: Param?): Completable {
        checkNotNull(param){"You can't save a null item"}
        return repository.saveAirport(param)
    }



    data class Param(
        var carriers: String?,
        var city: String?,
        var code: String,
        var country: String?,
        var directFlights: String?,
        var elev: String?,
        var email: String?,
        var icao: String?,
        var lat: String?,
        var lon: String?,
        var name: String?,
        var phone: String?,
        var runwayLength: String?,
        var state: String?,
        var type: String?,
        var tz: String?,
        var url: String?,
        var woeid: String?
    )
}