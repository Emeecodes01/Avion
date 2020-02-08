package com.mobigod.data.models.schedules

import com.google.gson.annotations.SerializedName

/**Created by: Emmanuel Ozibo
//on: 08, 2020-02-08
//at: 10:51*/
data class FlightScheduleResponse(
    @SerializedName("ScheduleResource")
    var scheduleResourceEntity: ScheduleResourceEntity)