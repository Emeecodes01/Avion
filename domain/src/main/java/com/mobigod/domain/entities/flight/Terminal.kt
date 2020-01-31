package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class Terminal(
    @SerializedName("Name")
    var name: String
)