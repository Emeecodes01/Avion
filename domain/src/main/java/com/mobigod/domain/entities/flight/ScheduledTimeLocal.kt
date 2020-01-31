package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class ScheduledTimeLocal(
    @SerializedName("DateTime")
    var dateTime: String
)