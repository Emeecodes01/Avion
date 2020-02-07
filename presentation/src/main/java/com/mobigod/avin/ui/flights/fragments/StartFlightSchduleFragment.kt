package com.mobigod.avin.ui.flights.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding3.view.clicks
import com.mobigod.avin.databinding.StartFlightSchedulesFragmentBinding

/**Created by: Emmanuel Ozibo
//on: 06, 2020-02-06
//at: 11:38*/
class StartFlightSchduleFragment: Fragment() {

    lateinit var binding: StartFlightSchedulesFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StartFlightSchedulesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.button.setOnClickListener {
//            val action = StartFlightSchduleFragmentDirections.actionStartScheduleSearchToLocDesFragment()
//            it.findNavController().navigate(action)
//        }
    }
}