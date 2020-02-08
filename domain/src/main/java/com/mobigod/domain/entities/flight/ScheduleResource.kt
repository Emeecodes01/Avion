package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class ScheduleResource(
    var meta: Meta,
    var schedule: List<Schedule>
)