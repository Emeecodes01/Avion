package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class Flight(
    @SerializedName("Arrival")
    var arrival: Arrival,
    @SerializedName("Departure")
    var departure: Departure,
    @SerializedName("Details")
    var details: Details,
    @SerializedName("Equipment")
    var equipment: Equipment,
    @SerializedName("MarketingCarrier")
    var marketingCarrier: MarketingCarrier
)