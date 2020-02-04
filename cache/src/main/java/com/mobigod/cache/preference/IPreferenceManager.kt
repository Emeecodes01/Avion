package com.mobigod.cache.preference

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 21:35*/
interface IPreferenceManager {
    var isFirstRun: Boolean
    var hasBeenSetUp: Boolean
    var accessToken: String
}