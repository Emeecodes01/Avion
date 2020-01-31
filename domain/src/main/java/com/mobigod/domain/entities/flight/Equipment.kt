package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class Equipment(
    @SerializedName("AircraftCode")
    var aircraftCode: String
)