package com.mobigod.remote

import com.mobigod.data.models.airport.AirportEntity

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 10:11*/
interface AirportService {
    fun getAirports(file: String): List<AirportEntity>
}