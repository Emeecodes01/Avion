package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class Details(
    @SerializedName("DatePeriod")
    var datePeriod: DatePeriod,
    @SerializedName("DaysOfOperation")
    var daysOfOperation: String,
    @SerializedName("Stops")
    var stops: Stops
)