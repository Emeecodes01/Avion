package com.mobigod.avin.mapper

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 20:56*/


interface BaseViewMapper<V, D>{
    fun mapFromViewModel(par: V): D
    fun mapToViewModel(par: D): V
}