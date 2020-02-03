package com.mobigod.data.tester

import com.mobigod.data.models.airport.AirportEntity
import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.usecases.airport.SaveAirportUseCase
import com.mobigod.domain.usecases.airport.SearchAirportUseCase
import konveyor.base.randomBuild

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 12:40*/
object StubGenerator {

    fun createAirportEntity(): AirportEntity = randomBuild()
    fun generateAirportParam(): SaveAirportUseCase.Param = randomBuild()
    fun generateSearchAirportParam(): SearchAirportUseCase.Params = randomBuild()


    fun generateListOfAirportEntity(count: Int): List<AirportEntity> {
        val l: MutableList<AirportEntity> = mutableListOf()
        repeat(count){
            l.add(createAirportEntity())
        }
        return l
    }


    fun generateListOfAirports(count: Int): List<Airport> {
        val r: MutableList<Airport> = mutableListOf()
        repeat(count){
            r.add(randomBuild())
        }

        return r
    }

}