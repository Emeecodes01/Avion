package com.mobigod.avin.ui.flights.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding3.view.clicks
import com.mobigod.avin.databinding.StartFlightSchedulesFragmentBinding
import com.mobigod.avin.ui.flights.FlightViewModel
import com.mobigod.avin.utils.showSnackMessage
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import java.util.*


/**Created by: Emmanuel Ozibo
//on: 06, 2020-02-06
//at: 11:38*/
class StartFlightSchduleFragment: Fragment(), DatePickerDialog.OnDateSetListener {

    lateinit var binding: StartFlightSchedulesFragmentBinding
    lateinit var viewModel: FlightViewModel
    private val subscriptions = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(this)[FlightViewModel::class.java]
        } ?: throw Exception("Invalid activity, it doesn't contain viewmodel of this type")
    }




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StartFlightSchedulesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListeners()
        subscribeToLiveData()
    }


    private fun subscribeToLiveData() {
        viewModel.originSyncLiveData.observe(viewLifecycleOwner, Observer {
            originAirport -> binding.origin = originAirport
        })

        viewModel.destinationSyncLiveData.observe(viewLifecycleOwner, Observer {
            destinationAirport -> binding.destination = destinationAirport
        })

        viewModel.flightDateSyncLiveData.observe(viewLifecycleOwner, Observer {
            flightDate -> binding.date = flightDate
        })
    }


    private fun setUpListeners() {
        binding.originCard.setOnClickListener {
            val action =
                StartFlightSchduleFragmentDirections.actionStartScheduleSearchToLocDesFragment()
            it.findNavController().navigate(action)
        }

        binding.destinationCard.setOnClickListener {
            val action =
                StartFlightSchduleFragmentDirections.actionStartScheduleSearchToLocDesFragment()
            it.findNavController().navigate(action)
        }

        binding.departureDate.setOnClickListener {
            showDataPickerDialog()
        }

        subscriptions += binding.searchFlightsBtn.clicks().subscribe {
            //validate inputs
            if (!viewModel.searchFlightScheduleHasIncompleteData()) {
                val directions = StartFlightSchduleFragmentDirections.actionStartScheduleSearchToFlightSchedulesFragment()
                findNavController().navigate(directions)
            }else {
                showSnackMessage("Oops, seems you forgot to input some values, check and retry")
            }

        }

    }



    private fun showDataPickerDialog() {
        val now = Calendar.getInstance()
        DatePickerDialog.newInstance(this,
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        ).apply {
            version = DatePickerDialog.Version.VERSION_2
            minDate = now
            setOkText("Okay")
            setOkColor("#ffffff")
            setCancelColor("#ffffff")
        }.also {
            it.show(activity!!.supportFragmentManager, "Datepickerdialog")
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (!subscriptions.isDisposed)
            subscriptions.clear()
    }



    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        viewModel.setFlightDate(year, monthOfYear, dayOfMonth)
    }

}