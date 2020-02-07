package com.mobigod.avin.ui.flights

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobigod.avin.mapper.AirportViewMapper
import com.mobigod.avin.models.airport.AirportModel
import com.mobigod.avin.states.Resource
import com.mobigod.avin.ui.flights.EditTextTypeState.DestinationState
import com.mobigod.avin.ui.flights.EditTextTypeState.OriginState
import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.usecases.airport.SearchAirportUseCase
import com.mobigod.domain.usecases.schedule.FlightScheduleUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 06, 2020-02-06
//at: 11:25*/
class FlightViewModel @Inject constructor(private val searchAirportUseCase: SearchAirportUseCase,
                                          private val mapper: AirportViewMapper): ViewModel(){

    private val disposeables = CompositeDisposable()

    val airportsLiveData: MutableLiveData<Resource<List<AirportModel>>> = MutableLiveData()

    val originTextSync: MutableLiveData<String> = MutableLiveData()
    val destinationTextSync: MutableLiveData<String> = MutableLiveData()
    val flightDateSyncLiveData: MutableLiveData<String> = MutableLiveData()
    val originSyncLiveData: MutableLiveData<AirportModel> = MutableLiveData()
    val destinationSyncLiveData: MutableLiveData<AirportModel> = MutableLiveData()


    private val textStatesManager: Stack<EditTextTypeState> = Stack()
    private val flightScheduleParam = FlightScheduleUseCase.Params()



    fun searchAirports(query: String) {
        val searchFor = "%$query%"
        val param = SearchAirportUseCase.Params(searchFor)
        searchAirportUseCase.execute(AirportsSingleObserver(), param)
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


    inner class AirportsSingleObserver : DisposableSingleObserver<List<Airport>>(){
        override fun onSuccess(airports: List<Airport>) {
            airportsLiveData.postValue(Resource.Success(airports.map{mapper.mapToViewModel(it)}))
        }

        override fun onError(e: Throwable) {
            airportsLiveData.postValue(Resource.Error(e.message))
        }
    }


    fun hasOriginAndDestinationParams() =
        flightScheduleParam.origin.isNotEmpty() && flightScheduleParam.destination.isNotEmpty()


    override fun onCleared() {
        super.onCleared()

        if (!disposeables.isDisposed)
            disposeables.clear()
    }




}