package com.mobigod.domain.entities.airport


import com.google.gson.annotations.SerializedName

data class Airport(
    @SerializedName("carriers")
    var carriers: String,
    @SerializedName("city")
    var city: String,
    @SerializedName("code")
    var code: String,
    @SerializedName("country")
    var country: String,
    @SerializedName("direct_flights")
    var directFlights: String,
    @SerializedName("elev")
    var elev: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("icao")
    var icao: String,
    @SerializedName("lat")
    var lat: String,
    @SerializedName("lon")
    var lon: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("runway_length")
    var runwayLength: String,
    @SerializedName("state")
    var state: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("tz")
    var tz: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("woeid")
    var woeid: String
)