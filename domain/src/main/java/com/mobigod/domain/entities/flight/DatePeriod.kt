package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class DatePeriod(
    @SerializedName("Effective")
    var effective: String,
    @SerializedName("Expiration")
    var expiration: String
)