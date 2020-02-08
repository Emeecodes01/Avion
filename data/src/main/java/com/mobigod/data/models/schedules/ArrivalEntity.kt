package com.mobigod.data.models.schedules

import com.google.gson.annotations.SerializedName

data class ArrivalEntity(
    @SerializedName("AirportCode")
    var AirportCode: String,
    @SerializedName("ScheduledTimeLocal")
    var ScheduledTimeLocalEntity: ScheduledTimeLocalEntity,
    @SerializedName("Terminal")
    var TerminalEntity: TerminalEntity
)