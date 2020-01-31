package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class ScheduleResource(
    @SerializedName("Meta")
    var meta: Meta,
    @SerializedName("Schedule")
    var schedule: List<Schedule>
)