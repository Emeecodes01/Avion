package com.mobigod.data.models.airport

import com.google.gson.annotations.SerializedName

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 00:29*/

data class AirportEntity(
    var carriers: String,
    var city: String,
    var code: String,
    var country: String,
    var directFlights: String,
    var elev: String,
    var email: String,
    var icao: String,
    var lat: String,
    var lon: String,
    var name: String,
    var phone: String,
    var runwayLength: String,
    var state: String,
    var type: String,
    var tz: String,
    var url: String,
    var woeid: String
)