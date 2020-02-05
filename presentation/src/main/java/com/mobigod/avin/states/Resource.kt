package com.mobigod.avin.states

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 23:20*/

sealed class Resource<T>(
    val state: State,
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(State.SUCCESS, data)
    class Loading<T>(data: T? = null) : Resource<T>(State.LOADING, data)
    class Error<T>(message: String?, data: T? = null) : Resource<T>(State.ERROR, data, message)
}