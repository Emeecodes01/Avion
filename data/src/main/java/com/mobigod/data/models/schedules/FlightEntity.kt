package com.mobigod.data.models.schedules

import com.google.gson.annotations.SerializedName

data class FlightEntity(
    @SerializedName("Arrival")
    var ArrivalEntity: ArrivalEntity,
    @SerializedName("Departure")
    var DepartureEntity: DepartureEntity
)