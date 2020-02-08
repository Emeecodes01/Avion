package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class Arrival(
    var airportCode: String,
    var scheduledTimeLocal: ScheduledTimeLocal,
    var terminal: Terminal
)