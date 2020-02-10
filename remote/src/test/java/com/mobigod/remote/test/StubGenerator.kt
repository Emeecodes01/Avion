package com.mobigod.remote.test

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mobigod.data.models.airport.AirportEntity
import com.mobigod.data.models.schedules.ScheduleEntity
import konveyor.base.randomBuild

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 18:23*/
object StubGenerator {

    fun generateListOfAirportEntities(jsonData: String): List<AirportEntity>{
        val gson = Gson()
        val airportListType = object : TypeToken<List<AirportEntity>>() {}.type
        return gson.fromJson(jsonData, airportListType)
    }

    fun generateListOfScheduleEntities(num: Int): List<ScheduleEntity> {
        val l: MutableList<ScheduleEntity> = mutableListOf()
        repeat(num){
            l.add(randomBuild())
        }
        return l
    }
}