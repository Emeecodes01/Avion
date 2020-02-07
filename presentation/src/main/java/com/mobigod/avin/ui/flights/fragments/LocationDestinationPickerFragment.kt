package com.mobigod.avin.ui.flights.fragments

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.jakewharton.rxbinding3.view.focusChanges
import com.jakewharton.rxbinding3.widget.afterTextChangeEvents
import com.jakewharton.rxbinding3.widget.textChanges
import com.mobigod.avin.databinding.LocDesInputBinding
import com.mobigod.avin.models.airport.AirportModel
import com.mobigod.avin.states.Resource
import com.mobigod.avin.states.State
import com.mobigod.avin.ui.adapters.AirportsAdapter
import com.mobigod.avin.ui.flights.EditTextTypeState
import com.mobigod.avin.ui.flights.EditTextTypeState.DestinationState
import com.mobigod.avin.ui.flights.EditTextTypeState.OriginState
import com.mobigod.avin.ui.flights.FlightViewModel
import com.mobigod.avin.utils.isNotEmpty
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**Created by: Emmanuel Ozibo
//on: 06, 2020-02-06
//at: 12:15*/
class LocationDestinationPickerFragment: Fragment() {

    private val TAG = LocationDestinationPickerFragment::class.java.simpleName

    lateinit var binding: LocDesInputBinding
    lateinit var viewModel: FlightViewModel


    private val subscribtion = CompositeDisposable()
    private val airportsAdapter = AirportsAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        binding = LocDesInputBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
                ViewModelProvider(this)[FlightViewModel::class.java]
        } ?: throw Exception("Invalid activity, it doesn't contain viewmodel of this type")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.airportsRv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = airportsAdapter
        }

        setUpTextChangeListeners()
        setUpLiveDataObservers()
        setUpOtherListeners()
        setUpFocusListeners()
    }



    /**
     * Receives item click event from airport adapter
     */
    private fun setUpOtherListeners() {
        subscribtion += airportsAdapter.clickPublisher.subscribe {
            airport ->
            /*val toast = Toast.makeText(context, "$airport", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
            toast.show()*/
            viewModel.updateFlightScheduleRequestParam(airport)
            if (viewModel.hasOriginAndDestinationParams() && hasFilledTextInputs()){
                val direction = LocationDestinationPickerFragmentDirections.actionLocDesFragmentToStartScheduleSearch()
                findNavController().navigate(direction)
            }
        }
    }

    private fun hasFilledTextInputs(): Boolean {
        return binding.destinationEd.isNotEmpty() && binding.originEdtxt.isNotEmpty()
    }


    /**
     * listens for input events
     */
    private fun setUpTextChangeListeners() {
        subscribtion += binding.originEdtxt.afterTextChangeEvents()
            .doOnSubscribe {
                binding.isLoading = true
            }
            .debounce(300, TimeUnit.MILLISECONDS)
            .map { it.view.text }
            .filter { it.isNotEmpty() && it.length >= 3 }
            .doOnNext {
                binding.isLoading = true
                airportsAdapter.clearAll()
            }
            .subscribe {
                origin ->
                //todo: call view model to get airports based on this origin
                Log.i(TAG, "----- $origin -----")
                viewModel.searchAirports(origin.toString())
            }


        subscribtion += binding.destinationEd.afterTextChangeEvents()
            .doOnSubscribe {
                binding.isLoading = true
            }
            .debounce(300, TimeUnit.MILLISECONDS)
            .map { it.view.text }
            .filter { it.isNotEmpty() && it.length >= 3 }
            .doOnNext {
                binding.isLoading = true
                airportsAdapter.clearAll()
            }
            .subscribe {
                    origin ->
                //todo: call view model to get airports based on this origin
                Log.i(TAG, "----- $origin -----")
                viewModel.searchAirports(origin.toString())
            }


    }


    /**
     * Waits and emits focus changes
     */
    private fun setUpFocusListeners() {
        subscribtion += binding.originEdtxt.focusChanges()
            .subscribe {
                    isFocused ->
                if (isFocused)
                    viewModel.setCurrentTextState(OriginState)
            }


        subscribtion += binding.destinationEd.focusChanges()
            .subscribe {
                    isFocused ->
                if (isFocused) {
                    airportsAdapter.clearAll()
                    viewModel.setCurrentTextState(DestinationState)
                }

            }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (!subscribtion.isDisposed)
            subscribtion.clear()
    }



    /**
     * whenever our data is ready from the viewmodel, this gets called
     */
    private fun setUpLiveDataObservers() {
        viewModel.airportsLiveData.observe(viewLifecycleOwner, Observer {
            airports ->
            processResults(airports)
        })


        viewModel.originTextSync.observe(viewLifecycleOwner, Observer {
            origin -> binding.originEdtxt.setText(origin)
        })


        viewModel.destinationTextSync.observe(viewLifecycleOwner, Observer {
            destination -> binding.destinationEd.setText(destination)
        })
    }


    /**
     * Handles our view state, depending on the data
     */
    private fun processResults(airports: Resource<List<AirportModel>>?) {
        airports?.let {
            when(airports.state){
                State.SUCCESS -> successState(airports.data)
                State.LOADING -> loadingState()
                State.ERROR -> errorState(airports.message)
            }
        }

    }



    private fun errorState(message: String?) {
        Snackbar.make(binding.root, message?:"An error has occurred", Snackbar.LENGTH_LONG).show()
    }

    private fun loadingState() {
        binding.isLoading=true
    }

    private fun successState(airports: List<AirportModel>?) {
        binding.isLoading = false
        if (airports != null)
            airportsAdapter.setAirports(airports)
    }

}