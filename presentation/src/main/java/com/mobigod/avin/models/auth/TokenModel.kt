package com.mobigod.avin.models.auth

import com.google.gson.annotations.SerializedName

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 20:55*/
class TokenModel(
    var accessToken: String,
    var expiresIn: Int,
    var tokenType: String
)