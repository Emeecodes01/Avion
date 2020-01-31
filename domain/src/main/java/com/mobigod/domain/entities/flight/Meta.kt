package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("Link")
    var link: List<Link>,
    @SerializedName("@Version")
    var version: String
)