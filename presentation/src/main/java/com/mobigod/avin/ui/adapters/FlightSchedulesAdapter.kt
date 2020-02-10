package com.mobigod.avin.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobigod.avin.databinding.FlightSchedulesItemLayoutBinding
import com.mobigod.avin.models.airport.AirportModel
import com.mobigod.avin.models.schedule.FlightModel
import com.mobigod.avin.models.schedule.ScheduleModel
import com.mobigod.avin.models.schedule.TotalJourneyModel
import com.mobigod.avin.utils.Tools
import io.reactivex.subjects.PublishSubject

/**Created by: Emmanuel Ozibo
//on: 08, 2020-02-08
//at: 04:13*/
class FlightSchedulesAdapter: RecyclerView.Adapter<FlightSchedulesAdapter.FlightSchedulesViewHolder>(){
    private val flightSchedules: MutableList<ScheduleModel> = mutableListOf()
    private lateinit var binding: FlightSchedulesItemLayoutBinding


    /**
     * in the fragment class subscribe to this observable,
     * clicks will be handled in the layout
     */
    var clickPublisher = PublishSubject.create<List<FlightModel>>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightSchedulesViewHolder {
        binding = FlightSchedulesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightSchedulesViewHolder(binding.root)
    }


    override fun getItemCount() = flightSchedules.size


    override fun onBindViewHolder(holder: FlightSchedulesViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val flightModels = flightSchedules[position].FlightModel

        binding.flightDurationTv.setFlightDuration = Tools.getFlightDuration(flightSchedules[position].TotalJourneyModel.Duration)

        binding.flightItineraryLayout.setFlightData(flightModels)


        binding.clickPublisher = clickPublisher
        binding.flights = flightModels
    }


    fun addSchedules(schedules: List<ScheduleModel> = listOf()) {
        if (flightSchedules.isNotEmpty())
            flightSchedules.clear()

        flightSchedules.addAll(schedules)
        notifyDataSetChanged()
    }


    class FlightSchedulesViewHolder(item: View): RecyclerView.ViewHolder(item)
}