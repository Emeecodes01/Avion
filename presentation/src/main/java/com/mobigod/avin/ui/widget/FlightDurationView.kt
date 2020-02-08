package com.mobigod.avin.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.mobigod.avin.databinding.FlightDurationLayoutBinding

/**Created by: Emmanuel Ozibo
//on: 07, 2020-02-07
//at: 22:14*/
class FlightDurationView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, style: Int = 0):
    LinearLayout(context, attributeSet, style) {

    var binding: FlightDurationLayoutBinding =
        FlightDurationLayoutBinding.inflate(LayoutInflater.from(context), this, false)

    var setFlightDuration: String = ""
        set(value) {
            binding.flightDurationTv.text = value
            field = value
        }



    init {
        addView(binding.root)
    }
}