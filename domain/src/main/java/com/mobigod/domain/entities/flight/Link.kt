package com.mobigod.domain.entities.flight


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("@Href")
    var href: String,
    @SerializedName("@Rel")
    var rel: String
)