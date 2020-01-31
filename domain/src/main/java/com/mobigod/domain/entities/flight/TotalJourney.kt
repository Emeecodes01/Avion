package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class TotalJourney(
    @SerializedName("Duration")
    var duration: String
)