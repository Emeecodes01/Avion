package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class Departure(
    @SerializedName("AirportCode")
    var airportCode: String,
    @SerializedName("ScheduledTimeLocal")
    var scheduledTimeLocal: ScheduledTimeLocalX
)