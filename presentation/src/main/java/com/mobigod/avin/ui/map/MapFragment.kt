package com.mobigod.avin.ui.map

import android.os.Bundle
import android.os.Handler
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

    private  var latLngBound: LatLngBounds.Builder? = null
    private var polylineOptions: PolylineOptions? = null


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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mapView.getMapAsync(this)

        polylineOptions = PolylineOptions()
            .apply {
                pattern(listOf(Dot()))
                width(7f)
            }

        latLngBound = LatLngBounds.builder()

        subscribeToLiveData()
        setUpListeners()
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
        clearResources()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }


    private fun setUpListeners() {
        binding.mapToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }


    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0

        prepareAndStartMapDrawing()
    }


    private fun prepareAndStartMapDrawing() {
        val codesHolder = args.airportCodes
        for (codeHolder in codesHolder) {
            viewmodel.getAirportsWithCodes(
                listOf(
                    codeHolder.departureAirportCode,
                    codeHolder.arrivalAirportCode
                )
            )
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

                polylineOptions?.add(LatLng(latitude, longitude))
                //generate all maps utils here
                addMakerToGoogleMap(longitude, latitude, makerDescription)
            }

            googleMap?.addPolyline(polylineOptions)
            val camUpdate = CameraUpdateFactory.newLatLngBounds(latLngBound?.build(), 70)
            googleMap?.animateCamera(camUpdate)

        }

    }


    private fun addMakerToGoogleMap(longitude: Double, latitude: Double, description: String) {
        val latLng = LatLng(latitude, longitude)
        latLngBound?.include(latLng)

        MarkerOptions().apply {
            position(latLng)
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


    private fun clearResources() {
        latLngBound = null
        polylineOptions = null
    }
}
