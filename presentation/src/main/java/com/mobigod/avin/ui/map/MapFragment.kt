package com.mobigod.avin.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import com.google.android.material.snackbar.Snackbar
import com.google.maps.android.PolyUtil
import com.mobigod.avin.databinding.MapsFragmentLayoutBinding
import com.mobigod.avin.models.airport.AirportModel
import com.mobigod.avin.models.schedule.ScheduleModel
import com.mobigod.avin.states.State
import com.mobigod.avin.ui.flights.FlightViewModel
import com.mobigod.avin.utils.hide
import com.mobigod.avin.utils.show

/**Created by: Emmanuel Ozibo
//on: 08, 2020-02-08
//at: 21:24*/

class MapFragment: Fragment(), OnMapReadyCallback{

    lateinit var binding: MapsFragmentLayoutBinding
    private var googleMap: GoogleMap? = null
    lateinit var viewmodel: FlightViewModel

    private val args: MapFragmentArgs by navArgs()
    private val polylineOptions = PolylineOptions()
    private var cameraLatLngBounds = LatLngBounds.builder()
    private val latLngs: MutableList<LatLng> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel = activity?.run {
            ViewModelProvider(this)[FlightViewModel::class.java]
        } ?: throw Exception("Invalid activity, it doesn't contain viewmodel of this type")


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MapsFragmentLayoutBinding.inflate(inflater, container, false)
        binding.mapView.onCreate(savedInstanceState)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }


    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mapView.getMapAsync(this)
        subscribeToLiveData()
        setUpListeners()
    }


    private fun setUpListeners() {
        binding.mapToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun subscribeToLiveData() {
        viewmodel.airportsWithCodeLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                res -> when(res.state) {
                State.LOADING -> loadingState()
                State.ERROR -> errorState(res.message)
                State.SUCCESS -> successState(res.data)
            }
            }
        })
    }


    private fun successState(data: List<AirportModel>?) {
        binding.progressBar.hide()
        data?.run {
            forEach {
                airport ->
                val longitude = airport.lon!!.toDouble()
                val latitude = airport.lat!!.toDouble()
                val makerDescription = "${airport.code}, ${airport.city}"
                addMakerToGoogleMap(longitude, latitude, makerDescription)
                addPositionToPloylineOption(latitude, longitude)
                includeCoordinateInBoundsOfCamera(latitude, longitude)
            }


            //After the loop
            polylineOptions.also {
                pOptions ->
                styleMap(pOptions)
                googleMap?.addPolyline(pOptions)
            }

            updateLatLngZoomOfGoogleMap()
        }
    }

    private fun styleMap(pOptions: PolylineOptions){
        pOptions.pattern(listOf(Dot()))
        pOptions.width(10f)
        pOptions.startCap(ButtCap())
        pOptions.endCap(RoundCap())
    }

    private fun includeCoordinateInBoundsOfCamera(latitude: Double, longitude: Double) {
        cameraLatLngBounds.include(LatLng(latitude, longitude))
    }

    private fun updateLatLngZoomOfGoogleMap(){
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngBounds(cameraLatLngBounds.build(), 100))
    }

    private fun addPositionToPloylineOption(latitude: Double, longitude: Double) {
        polylineOptions.add(LatLng(latitude, longitude))

    }


    private fun addMakerToGoogleMap(longitude: Double, latitude: Double, description: String) {
        MarkerOptions().apply {
            position(LatLng(latitude, longitude))
            title(description)
        }.also {
            googleMap?.addMarker(it)
        }
    }


    private fun errorState(message: String?) {
        binding.progressBar.hide()
        Snackbar.make(binding.root, "$message", Snackbar.LENGTH_LONG)
            .setActionTextColor(ContextCompat.getColor(context!!, android.R.color.white))
            .show()
    }


    private fun loadingState() {
        binding.progressBar.show()
    }


    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0

        val departureAirportCode = args.originAirportCode
        val arrivalAirportCode = args.destinationAirportCode

        viewmodel.getAirportsWithCodes(listOf(departureAirportCode, arrivalAirportCode))
    }


}