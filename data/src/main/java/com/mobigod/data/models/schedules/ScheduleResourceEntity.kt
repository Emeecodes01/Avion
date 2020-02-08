package com.mobigod.data.models.schedules

import com.google.gson.annotations.SerializedName

data class ScheduleResourceEntity(
    @SerializedName("Meta")
    var MetaEntity: MetaEntity,
    @SerializedName("Schedule")
    var ScheduleEntity: List<ScheduleEntity>
)