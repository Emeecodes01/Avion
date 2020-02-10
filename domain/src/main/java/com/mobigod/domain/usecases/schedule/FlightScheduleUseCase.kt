package com.mobigod.domain.usecases.schedule

import com.google.gson.annotations.SerializedName
import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import com.mobigod.domain.entities.flight.Schedule
import com.mobigod.domain.repository.IFlightSchedulesRepository
import com.mobigod.domain.usecases.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

open class FlightScheduleUseCase @Inject constructor( val repository: IFlightSchedulesRepository,
                                                 val threadExecutor: ThreadExecutor,
                                                      val postExecutionThread: PostExecutionThread):
    SingleUseCase<List<Schedule>, FlightScheduleUseCase.Params>(threadExecutor, postExecutionThread){



    override fun buildUseCaseObservable(param: Params?): Single<List<Schedule>> {
        checkNotNull(param){"Flight schedule params cannot be null"}
        assert(param.origin.isNotEmpty() && param.destination.isNotEmpty() && param.dateOfDeparture.isNotEmpty()){
            "Some data field(s) is empty, please fill up"
        }
        return repository.getFlightSchedules(param)
    }


    data class Params(
        var origin: String ="",
        var destination: String ="",
        var dateOfDeparture: String ="",
        var directFlight: Boolean = true
    )


}