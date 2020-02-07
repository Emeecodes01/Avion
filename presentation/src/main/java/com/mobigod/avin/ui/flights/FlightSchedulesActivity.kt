package com.mobigod.avin.ui.flights

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.mobigod.avin.R
import com.mobigod.avin.base.BaseActivity
import com.mobigod.avin.databinding.ActivityFlightSchedulesBinding
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 06, 2020-02-06
//at: 11:27*/


class FlightSchedulesActivity: BaseActivity<ActivityFlightSchedulesBinding>() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    lateinit var viewModel: FlightViewModel//This viewModel is share among it's fragments

    lateinit var binding: ActivityFlightSchedulesBinding

    override fun layoutRes() = R.layout.activity_flight_schedules


    override fun initComponents() {
        binding = getBinding()

        viewModel = ViewModelProvider(this, viewModelProviderFactory)[FlightViewModel::class.java]
    }


    companion object {
        fun start(context: Context) {
            Intent(context, FlightSchedulesActivity::class.java).also {
                context.startActivity(it)
            }
        }
    }

}