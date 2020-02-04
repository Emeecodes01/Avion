package com.mobigod.domain.entities.token


import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("access_token")
    var accessToken: String,
    @SerializedName("expires_in")
    var expiresIn: Int,
    @SerializedName("token_type")
    var tokenType: String
)