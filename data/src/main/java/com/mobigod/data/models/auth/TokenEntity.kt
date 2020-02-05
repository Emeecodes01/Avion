package com.mobigod.data.models.auth

import com.google.gson.annotations.SerializedName

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 08:57*/
class TokenEntity(
    @SerializedName("access_token")
    var accessToken: String,
    @SerializedName("expires_in")
    var expiresIn: Int,
    @SerializedName("token_type")
    var tokenType: String
)