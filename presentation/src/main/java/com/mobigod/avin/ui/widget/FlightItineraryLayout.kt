package com.mobigod.avin.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.ViewDataBinding
import com.mobigod.avin.databinding.DividerBinding
import com.mobigod.avin.databinding.FlightItemLayoutBinding
import com.mobigod.avin.models.schedule.FlightModel
import com.mobigod.avin.models.schedule.TotalJourneyModel
import com.mobigod.avin.ui.flights.FlightViewModel
import com.mobigod.avin.utils.Tools

/**Created by: Emmanuel Ozibo
//on: 09, 2020-02-09
//at: 23:21*/
class FlightItineraryLayout constructor(context: Context, attributeSet: AttributeSet?): LinearLayout(context, attributeSet) {

    init {
        orientation = VERTICAL
    }



    fun setFlightData(flights: List<FlightModel>) {
        flights.forEachIndexed {
            index, flightViewModel ->
            addView(bindDataToLayout(flightViewModel, ViewType.LAYOUT).root)

            /**
             *Don't draw the divider at the end of the last item
             */
            if (index < flights.size)
                addView(bindDataToLayout(flightViewModel, ViewType.DIVIDER).root)
        }
    }


    private fun createBindingLayout(viewType: ViewType): ViewDataBinding {
        return when(viewType){
            ViewType.LAYOUT -> FlightItemLayoutBinding.inflate(LayoutInflater.from(context), this, false)
            ViewType.DIVIDER -> DividerBinding.inflate(LayoutInflater.from(context), this, false)
        }
    }



    private fun bindDataToLayout(flightModel: FlightModel, type: ViewType): ViewDataBinding {
        val layout = createBindingLayout(type)

        if (layout is FlightItemLayoutBinding) {
            layout.flight = flightModel

            val departureDtString = flightModel.DepartureModel.scheduledTimeLocalModel.DateTime
            val arrivalDtString = flightModel.ArrivalModel.ScheduledTimeLocalModel.DateTime

            val readableDepartureDate = Tools.getDataFromString(departureDtString)
            val readableArrivalDate = Tools.getDataFromString(arrivalDtString)

            val readableDepartureTime = Tools.getTimeFromString(departureDtString)
            val readableArrivalTime = Tools.getTimeFromString(arrivalDtString)

            //layout.flightDurationTv.setFlightDuration = Tools.getFlightDuration()

            layout.departureDate = readableDepartureDate
            layout.departureTime = readableDepartureTime

            layout.arrivalDate = readableArrivalDate
            layout.arrivalTime = readableArrivalTime
        }

        return layout
    }
}