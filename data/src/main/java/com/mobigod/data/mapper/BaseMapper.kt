package com.mobigod.data.mapper

/**Created by: Emmanuel Ozibo
//on: 31, 2020-01-31
//at: 21:55*/
interface BaseMapper<E, D> {
    fun mapFromEntity(entity: E): D
    fun mapToEntity(domain: D): E
}