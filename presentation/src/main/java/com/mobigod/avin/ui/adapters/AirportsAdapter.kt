package com.mobigod.avin.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobigod.avin.databinding.AirportItemLayoutBinding
import com.mobigod.avin.models.airport.AirportModel
import io.reactivex.subjects.PublishSubject

/**Created by: Emmanuel Ozibo
//on: 06, 2020-02-06
//at: 21:45*/
class AirportsAdapter: RecyclerView.Adapter<AirportsAdapter.AirportViewHolder>(){
    private val airports: MutableList<AirportModel> = mutableListOf()
    private lateinit var airportBinding: AirportItemLayoutBinding

    /**
     * in the fragment class subscribe to this observable,
     * clicks will be handled in the layout
     */
    var clickPublisher = PublishSubject.create<AirportModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirportViewHolder {
        airportBinding = AirportItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AirportViewHolder(airportBinding.root)
    }

    override fun getItemCount() = airports.size

    override fun onBindViewHolder(holder: AirportViewHolder, position: Int) {
        airportBinding.airport = airports[position]
        airportBinding.clickPublisher = clickPublisher
        holder.setIsRecyclable(false)
    }

    fun setAirports(airportList: List<AirportModel> = listOf()) {
        if (airports.isNotEmpty())
            airports.clear()

        airports.addAll(airportList)
        notifyDataSetChanged()
    }

    fun clearAll() {
        airports.clear()
    }

    inner class AirportViewHolder(item: View): RecyclerView.ViewHolder(item) {
        //I don't even know what to do with you again
    }

}
