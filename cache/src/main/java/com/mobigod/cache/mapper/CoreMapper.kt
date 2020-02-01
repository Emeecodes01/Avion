package com.mobigod.cache.mapper

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 21:00*/

/**
 * X => Db entities
 * Y => Other entities
 */
interface CoreMapper<X, Y> {
    fun mapFromDbEntity(value: X): Y
    fun mapToDbEntity(value: Y): X
}