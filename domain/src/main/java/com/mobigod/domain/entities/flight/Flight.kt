package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class Flight(
    var arrival: Arrival,
    var departure: Departure
)