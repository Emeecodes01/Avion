package com.mobigod.avin.presenter.mapper

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 16:16*/


/**
 * @param <V> the view model input type
 * @param <D> the domain model output type
 */
interface BaseMapper<out V, in D> {
    fun mapToView(type: D): V
}