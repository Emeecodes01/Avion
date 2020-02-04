package com.mobigod.data.models.auth

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 08:57*/
class TokenEntity(
    var accessToken: String,
    var expiresIn: Int,
    var tokenType: String
)