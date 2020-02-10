package com.mobigod.avin.ui.flights.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobigod.avin.databinding.FlightSchedulesLayoutBinding
import com.mobigod.avin.models.airport.AirportCodesHolder
import com.mobigod.avin.models.airport.AirportModel
import com.mobigod.avin.models.schedule.ScheduleModel
import com.mobigod.avin.states.State
import com.mobigod.avin.ui.adapters.FlightSchedulesAdapter
import com.mobigod.avin.ui.flights.FlightViewModel
import com.mobigod.avin.utils.hide
import com.mobigod.avin.utils.show
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.coroutines.delay

/**Created by: Emmanuel Ozibo
//on: 07, 2020-02-07
//at: 18:42*/
class FlightSchedulesFragment: Fragment() {

    private lateinit var binding: FlightSchedulesLayoutBinding
    lateinit var viewmodel: FlightViewModel

    private val disposable = CompositeDisposable()

    private lateinit var scheduleAdapter: FlightSchedulesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel = activity?.run {
            ViewModelProvider(this)[FlightViewModel::class.java]
        } ?: throw Exception("Invalid activity, it doesn't contain viewmodel of this type")

        /**
         * Just to wait for the fragment creation process to finish
         */
        Handler().postDelayed({
            viewmodel.getFlightSchedules()
        }, 200)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FlightSchedulesLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scheduleAdapter = FlightSchedulesAdapter()

        binding.schedulesRv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = scheduleAdapter
        }


        setUpListeners()
        setUpLiveDataObservers()
        setUpRxObservers()

    }


    private fun setUpRxObservers() {
        disposable += scheduleAdapter.clickPublisher.subscribe {
            flightModels ->
            val codeHolders: MutableList<AirportCodesHolder> = mutableListOf()

            for (flightModel in flightModels) {
                codeHolders.add(AirportCodesHolder(flightModel.DepartureModel.AirportCode, flightModel.ArrivalModel.AirportCode))
            }

            /**
             * I decided to pass just the code rather than the entire object,
             * Just for optimization
             */
            val direction = FlightSchedulesFragmentDirections
                .actionFlightSchedulesFragmentToMapFragment(codeHolders.toTypedArray())

            findNavController().navigate(direction)
        }
    }


    private fun setUpLiveDataObservers() {
        viewmodel.flightDateSyncLiveData.observe(viewLifecycleOwner, Observer {
            binding.setDepartureDate(it)
        })

        viewmodel.originSyncLiveData.observe(viewLifecycleOwner, Observer {origin->
            binding.origin = origin
        })

        viewmodel.destinationSyncLiveData.observe(viewLifecycleOwner, Observer {destination ->
            binding.destination = destination
        })


        viewmodel.flightSchedulesLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                res -> when(res.state){
                State.LOADING -> loadingState()
                State.ERROR -> errorState(res.message)
                State.SUCCESS -> successState(res.data)
            }
            }
        })
    }

    private fun successState(data: List<ScheduleModel>?) {
        binding.progressBar.hide()
        binding.errorLayout.hide()
        binding.schedulesRv.show()
        binding.flightSchedules.hide()

        data?.run { scheduleAdapter.addSchedules(data) }
    }


    private fun loadingState() {
        binding.progressBar.show()
        binding.errorLayout.hide()
        binding.schedulesRv.hide()
        binding.flightSchedules.show()
    }

    private fun errorState(message: String?) {
        binding.errTv.text = message
        binding.progressBar.hide()
        binding.errorLayout.show()
        binding.schedulesRv.hide()
        binding.flightSchedules.hide()
    }



    private fun setUpListeners() {
        binding.schedulesToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }



    override fun onDestroy() {
        super.onDestroy()

        if (!disposable.isDisposed)
            disposable.clear()
    }

}
