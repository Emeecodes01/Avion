package com.mobigod.data.models.schedules

import com.google.gson.annotations.SerializedName

data class DepartureEntity(
    @SerializedName("AirportCode")
    var AirportCode: String,
    @SerializedName("ScheduledTimeLocal")
    var scheduledTimeLocalEntity: ScheduledTimeLocalXEntity
)