package com.mobigod.cache.test

import com.mobigod.cache.models.AirportDBEntity
import com.mobigod.data.models.airport.AirportEntity
import konveyor.base.randomBuild

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 21:15*/
object StubsGenerator {

    fun stubAirportEntity(): AirportEntity = randomBuild()
    fun stubAirportDbEntity(): AirportDBEntity = randomBuild()
}