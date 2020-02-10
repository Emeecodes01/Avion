package com.mobigod.avin.ui.flights

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.mobigod.avin.mapper.AirportViewMapper
import com.mobigod.avin.mapper.FlightScheduleViewMapper
import com.mobigod.avin.models.airport.AirportCodesHolder
import com.mobigod.avin.models.airport.AirportModel
import com.mobigod.avin.models.schedule.ScheduleModel
import com.mobigod.avin.states.Resource
import com.mobigod.avin.ui.flights.EditTextTypeState.DestinationState
import com.mobigod.avin.ui.flights.EditTextTypeState.OriginState
import com.mobigod.avin.utils.network.ErrorHandler
import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.entities.flight.Schedule
import com.mobigod.domain.usecases.airport.GetAirportsWithCodesUseCase
import com.mobigod.domain.usecases.airport.SearchAirportUseCase
import com.mobigod.domain.usecases.schedule.FlightScheduleUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.toObservable
import retrofit2.HttpException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 06, 2020-02-06
//at: 11:25*/

/**
 * Well this view model is shared by all fragments in the navigation graph, this makes data sharing and
 * syncing easier
 */
class FlightViewModel @Inject constructor(val searchAirportUseCase: SearchAirportUseCase,
                                          val flightScheduleUseCase: FlightScheduleUseCase,
                                          val getAirportsWithCodesUseCase: GetAirportsWithCodesUseCase,
                                          val mapper: AirportViewMapper,
                                          val schedulesMapper: FlightScheduleViewMapper): ViewModel() {

    private val disposeables = CompositeDisposable()

    val airportsLiveData: MutableLiveData<Resource<List<AirportModel>>> = MutableLiveData()

    val originTextSync: MutableLiveData<String> = MutableLiveData()
    val destinationTextSync: MutableLiveData<String> = MutableLiveData()
    val flightDateSyncLiveData: MutableLiveData<String> = MutableLiveData()
    val originSyncLiveData: MutableLiveData<AirportModel> = MutableLiveData()
    val destinationSyncLiveData: MutableLiveData<AirportModel> = MutableLiveData()
    val flightSchedulesLiveData: MutableLiveData<Resource<List<ScheduleModel>>> = MutableLiveData()
    val airportsWithCodeLiveData: MutableLiveData<Resource<List<AirportModel>>> = MutableLiveData()


    private val textStatesManager: Stack<EditTextTypeState> = Stack()
    private val flightScheduleParam = FlightScheduleUseCase.Params()



    fun searchAirports(query: String) {
        val searchFor = "%$query%"
        val param = SearchAirportUseCase.Params(searchFor)
        searchAirportUseCase.execute(AirportsSingleObserver(), param)
    }


    fun getFlightSchedules() {
        flightSchedulesLiveData.postValue(Resource.Loading())

        flightScheduleUseCase.execute(FlightSchedulesObserver(), flightScheduleParam)
    }


    /**
     * ensure you aren't getting shitty values
     */
    fun searchFlightScheduleHasIncompleteData() =
        flightScheduleParam.origin.isEmpty() || flightScheduleParam.destination.isEmpty()
                || flightScheduleParam.dateOfDeparture.isEmpty()


    fun getAirportsWithCodes(codes: List<String>) {
        val param = GetAirportsWithCodesUseCase.Param(codes)
        getAirportsWithCodesUseCase.execute(GetAirportsWithCodesObserver(), param)
    }


    /**
     * This is called when focus changes in the text field
     */
    fun setCurrentTextState(state: EditTextTypeState) {
        textStatesManager.push(state)
    }



    fun updateFlightScheduleRequestParam(airport: AirportModel) {
        when(textStatesManager.peek()){
            OriginState -> {
                flightScheduleParam.origin = airport.code
                originTextSync.value = airport.name
                originSyncLiveData.value = airport
            }

            DestinationState -> {
                flightScheduleParam.destination = airport.code
                destinationTextSync.value = airport.name
                destinationSyncLiveData.value = airport
            }
        }
    }



    fun setFlightDate(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val calender = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, monthOfYear)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
        val dateString = SimpleDateFormat("YYYY-MM-dd", Locale.ENGLISH).format(calender.time)
        flightScheduleParam.dateOfDeparture = dateString
        flightDateSyncLiveData.value = dateString
    }



    fun hasOriginAndDestinationParams() =
        flightScheduleParam.origin.isNotEmpty() && flightScheduleParam.destination.isNotEmpty()


    //================================================ OBSERVER CLASSES ===================================================//
    inner class AirportsSingleObserver : DisposableSingleObserver<List<Airport>>() {
        override fun onSuccess(airports: List<Airport>) {
            airportsLiveData.postValue(Resource.Success(airports.map{mapper.mapToViewModel(it)}))
        }

        override fun onError(e: Throwable) {
            airportsLiveData.postValue(Resource.Error(e.message))
        }
    }



    inner class FlightSchedulesObserver: DisposableSingleObserver<List<Schedule>>() {
        override fun onSuccess(schedules: List<Schedule>) {
            flightSchedulesLiveData.postValue(Resource.Success(schedules.map {
                    schedule -> schedulesMapper.mapToViewModel(schedule)}))
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
            val errorMessage = ErrorHandler.getErrorMessage(e)
            flightSchedulesLiveData.postValue(Resource.Error(errorMessage))
        }
    }


    inner class GetAirportsWithCodesObserver: DisposableSingleObserver<List<Airport>>() {
        override fun onSuccess(t: List<Airport>) {
            airportsWithCodeLiveData.postValue(Resource.Success(t.map { mapper.mapToViewModel(it) }))
        }

        override fun onError(e: Throwable) {
            val errMessage = ErrorHandler.getErrorMessage(e)
            airportsWithCodeLiveData.postValue(Resource.Error(errMessage))
        }
    }
    //================================================ OBSERVER CLASSES ===================================================//

    override fun onCleared() {
        super.onCleared()

        if (!disposeables.isDisposed)
            disposeables.clear()
    }


}