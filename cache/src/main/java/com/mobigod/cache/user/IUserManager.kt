package com.mobigod.cache.user

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 10:53*/
interface IUserManager {
    fun saveUserClientId(clientId: String)
    fun saveUserClientSecret(secret: String)
    fun getUserClientId(): String
    fun getUserClientSecret(): String
}