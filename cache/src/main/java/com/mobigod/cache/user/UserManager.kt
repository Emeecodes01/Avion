package com.mobigod.cache.user

import com.mobigod.cache.preference.IPreferenceManager
import com.mobigod.cache.preference.PreferenceManager
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 10:55*/
class UserManager @Inject constructor(val preferenceManager: IPreferenceManager): IUserManager {

    override fun saveUserClientId(clientId: String) {
        preferenceManager.userClientId = clientId
    }

    override fun saveUserClientSecret(secret: String) {
        preferenceManager.userClientSecret = secret
    }

    override fun getUserClientId() = preferenceManager.userClientId

    override fun getUserClientSecret() = preferenceManager.userClientSecret

    override fun isUserAuthenticated() = preferenceManager.userAuthenticated

    override fun setUserAuthenticated() {
        preferenceManager.userAuthenticated = true
    }
}