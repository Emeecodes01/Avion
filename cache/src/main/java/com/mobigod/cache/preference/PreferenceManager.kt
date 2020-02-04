package com.mobigod.cache.preference

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 21:36*/

class PreferenceManager @Inject constructor(val context: Context): IPreferenceManager {

    companion object {
        private val PREF_AVION_PREF_PACKAGE_NAME = "com.mobigod.cache.preference"
        private val KEY_FIRST_RUN = "is_first_run"
        private val KEY_HAS_BEEN_SETUP = "has_been_setup"
        private val KEY_ACCESS_TOKEN = "_accessToken"
    }

    private val avionPreference: SharedPreferences

    init {
        avionPreference = context.getSharedPreferences(PREF_AVION_PREF_PACKAGE_NAME, Context.MODE_PRIVATE)
    }


    override var isFirstRun: Boolean
        get() = avionPreference.getBoolean(KEY_FIRST_RUN, false)
        set(value) {avionPreference.edit().putBoolean(KEY_FIRST_RUN, value).apply()}


    override var hasBeenSetUp: Boolean
        get() = avionPreference.getBoolean(KEY_HAS_BEEN_SETUP, false)
        set(value) {avionPreference.edit().putBoolean(KEY_HAS_BEEN_SETUP, value).apply()}


    /**
     * Take Note: This is a JSON string
     */
    override var accessToken: String
        get() = avionPreference.getString(KEY_ACCESS_TOKEN, "")!!
        set(value) {avionPreference.edit().putString(KEY_ACCESS_TOKEN, value).apply()}
}