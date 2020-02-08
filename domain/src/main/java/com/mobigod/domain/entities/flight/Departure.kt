package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class Departure(
    var airportCode: String,
    var scheduledTimeLocal: ScheduledTimeLocalX
)