package com.mobigod.avin.ui.flights.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mobigod.avin.databinding.FlightSchedulesLayoutBinding
import com.mobigod.avin.ui.flights.FlightViewModel

/**Created by: Emmanuel Ozibo
//on: 07, 2020-02-07
//at: 18:42*/
class FlightSchedulesFragment: Fragment() {

    private lateinit var binding: FlightSchedulesLayoutBinding
    lateinit var viewmodel: FlightViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel = activity?.run {
            ViewModelProvider(this)[FlightViewModel::class.java]
        } ?: throw Exception("Invalid activity, it doesn't contain viewmodel of this type")

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FlightSchedulesLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListeners()
        setUpLiveDataObservers()
    }


    private fun setUpLiveDataObservers() {

    }

    private fun setUpListeners() {

    }


}