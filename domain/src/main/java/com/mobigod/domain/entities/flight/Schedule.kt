package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class Schedule(
    var flight: Flight,
    var totalJourney: TotalJourney
)