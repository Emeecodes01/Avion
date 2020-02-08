package com.mobigod.data.models.schedules

import com.google.gson.annotations.SerializedName

data class ScheduleEntity(
    @SerializedName("Flight")
    var FlightEntity: FlightEntity,
    @SerializedName("TotalJourney")
    var TotalJourneyEntity: TotalJourneyEntity
)