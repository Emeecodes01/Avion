package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("Flight")
    var flight: Flight,
    @SerializedName("TotalJourney")
    var totalJourney: TotalJourney
)