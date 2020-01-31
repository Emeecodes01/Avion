package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class MarketingCarrier(
    @SerializedName("AirlineID")
    var airlineID: String,
    @SerializedName("FlightNumber")
    var flightNumber: String
)