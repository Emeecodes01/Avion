package com.mobigod.avin.ui.setup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobigod.avin.mapper.AirportViewMapper
import com.mobigod.avin.rx.AppSchedulers
import com.mobigod.avin.states.Resource
import com.mobigod.cache.preference.IPreferenceManager
import com.mobigod.cache.preference.PreferenceManager
import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.executors.ISchedulersFactory
import com.mobigod.domain.usecases.airport.GetAirportsUseCase
import com.mobigod.domain.usecases.airport.SaveAirportUseCase
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import javax.inject.Inject
import kotlin.math.roundToInt

/**Created by: Emmanuel Ozibo
//on: 05, 2020-02-05
//at: 15:11*/
class SetupViewModel @Inject constructor(private val getAirportsUseCase: GetAirportsUseCase,
                                         private val saveAirportUseCase: SaveAirportUseCase,
                                         private val rxSchedulers: ISchedulersFactory,
                                         private val preferenceManager: IPreferenceManager
                                         /*val AirportViewMapper: AirportViewMapper*/): ViewModel() {

    private val disposables = CompositeDisposable()
    private var numOfAirports = 0
    private var numOfSavedAirport = 0

    val percentageLiveData: MutableLiveData<Resource<Int>> = MutableLiveData()
    val loadingCompleteLiveData: MutableLiveData<Resource<Unit>> = MutableLiveData()

    fun startDeviceSetUp() {
        getAirportsUseCase.execute(AirportsDisposeable(), Unit)
    }


    private fun saveAllAirports(airports: List<Airport>) {
        numOfAirports = airports.size

//        disposables += airports.toObservable()
//            .flatMap {airport ->
//                val param = generateSaveAirportUsecaseParams(airport)
//                saveAirportUseCase.execute(param)
//                    .andThen(Observable.just(1))
//            }
//            .subscribeOn(rxSchedulers.io())
//            .observeOn(rxSchedulers.ui())
//            .subscribeBy(
//                onComplete = {
//                    numOfSavedAirport++
//                    val percentage = ((numOfSavedAirport / numOfAirports) * 100.0).roundToInt()
//                    Log.i("percent", percentage.toString())
//                    percentageLiveData.postValue(Resource.Success(percentage))
//                },
//
//                onError = {
//                    it.printStackTrace()
//                    percentageLiveData.postValue(Resource.Error(it.message))
//                }
//            )


        for (airport in airports) {
            val param = generateSaveAirportUsecaseParams(airport)
            disposables += saveAirportUseCase.execute(param)
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.ui())
                .subscribeBy(
                    onComplete = {
                        numOfSavedAirport++
                        val percentage = (numOfSavedAirport* 100.0 / numOfAirports).roundToInt()
                        Log.i("percent", percentage.toString())
                        percentageLiveData.postValue(Resource.Success(percentage))

                        if (numOfAirports == numOfSavedAirport)
                            loadingCompleteLiveData.postValue(Resource.Success(Unit))

                    },

                    onError = {
                        it.printStackTrace()
                        percentageLiveData.postValue(Resource.Error(it.message))
                    }
                )
        }
    }


    fun setUserSetUp() {
        preferenceManager.hasBeenSetUp = true
    }

    fun updateFirstTime() {
        preferenceManager.isFirstRun = false
    }

    private fun generateSaveAirportUsecaseParams(airport: Airport): SaveAirportUseCase.Param{
        return SaveAirportUseCase.Param(
            airport.carriers,
            airport.city,
            airport.code,
            airport.country,
            airport.directFlights,
            airport.elev,
            airport.email,
            airport.icao,
            airport.lat,
            airport.lon,
            airport.name,
            airport.phone,
            airport.runwayLength,
            airport.state,
            airport.type,
            airport.tz,
            airport.url,
            airport.woeid
        )
    }


    inner class AirportsDisposeable: DisposableSingleObserver<List<Airport>>(){
        override fun onSuccess(airports: List<Airport>) {
            saveAllAirports(airports)
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
            percentageLiveData.postValue(Resource.Error(e.message))
        }
    }




    override fun onCleared() {
        super.onCleared()

        if (!disposables.isDisposed)
            disposables.clear()
    }
}