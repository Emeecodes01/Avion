package com.mobigod.data.models.schedules

import com.google.gson.annotations.SerializedName

data class ScheduleEntity(
    @SerializedName("Flight")
    var FlightEntity: List<FlightEntity>,
    @SerializedName("TotalJourney")
    var TotalJourneyEntity: TotalJourneyEntity
)