package com.mobigod.domain.usecases.schedule

import com.google.gson.annotations.SerializedName
import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import com.mobigod.domain.entities.flight.FlightSchedulesResponse
import com.mobigod.domain.repository.IFlightSchedules
import com.mobigod.domain.usecases.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class FlightScheduleUseCase @Inject constructor(val repository: IFlightSchedules,
                                                private val threadExecutor: ThreadExecutor,
                                                private val postExecutionThread: PostExecutionThread):
    SingleUseCase<FlightSchedulesResponse, FlightScheduleUseCase.Params>(threadExecutor, postExecutionThread){



    override fun buildUseCaseObservable(param: Params?): Single<FlightSchedulesResponse> {
        checkNotNull(param){"Flight schedule params cannot be null"}
        assert(param.origin.isNotEmpty() && param.destination.isNotEmpty() && param.dateOfDeparture.isNotEmpty()){
            "Some data field(s) is empty, please fill up"
        }

        return repository.getFlightSchedules(param)
    }


    data class Params(
        @SerializedName("origin") val origin: String,
        @SerializedName("destination") val destination: String,
        @SerializedName("fromDateTime") val dateOfDeparture: String,
        @SerializedName("limit") val limit: String = "15"
    )


}