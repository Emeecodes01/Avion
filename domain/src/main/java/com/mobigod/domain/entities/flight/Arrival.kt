package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class Arrival(
    @SerializedName("AirportCode")
    var airportCode: String,
    @SerializedName("ScheduledTimeLocal")
    var scheduledTimeLocal: ScheduledTimeLocal,
    @SerializedName("Terminal")
    var terminal: Terminal
)