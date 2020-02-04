package com.mobigod.cache.models

import com.google.gson.Gson

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 07:16*/

data class TokenPrefEntity (
    val loggedInTime: Long = 0,
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int) {


    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }

}