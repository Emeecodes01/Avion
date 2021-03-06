package com.mobigod.avin.test

import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.entities.flight.Schedule
import konveyor.base.randomBuild

/**Created by: Emmanuel Ozibo
//on: 09, 2020-02-09
//at: 22:26*/

object PStubGenerator {

    fun getListOfAirports(count: Int): List<Airport> {
        val l: MutableList<Airport> = mutableListOf()
        repeat(count){
            l.add(randomBuild())
        }
        return l
    }

    fun generateSchduleList(count: Int): List<Schedule> {
        val l: MutableList<Schedule> = mutableListOf()
        repeat(count){
            l.add(randomBuild())
        }
        return l
    }

}