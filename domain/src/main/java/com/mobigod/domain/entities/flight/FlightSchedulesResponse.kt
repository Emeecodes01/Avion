package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class FlightSchedulesResponse(
    @SerializedName("ScheduleResource")
    var scheduleResource: ScheduleResource
)