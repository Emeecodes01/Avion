package com.mobigod.data.mapper

import com.mobigod.data.models.airport.AirportEntity
import com.mobigod.domain.entities.airport.Airport
import konveyor.base.randomBuild
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by: Emmanuel Ozibo
 * //on: 03, 2020-02-03
 * //at: 11:29
 */
class AirportMapperTest {
    lateinit var SUT: AirportMapper

    @Before
    fun setUp() {
        SUT = AirportMapper()
    }


    @Test
    fun `mapFromEntity correctly maps data objects to domain module object`(){
        val airportEntity: AirportEntity = randomBuild()
        val result = SUT.mapFromEntity(airportEntity)
        checkResult(airportEntity, result)
    }

    @Test
    fun `mapFromEntity correctly maps domain objects to data module object`(){
        val airport: Airport = randomBuild()
        val result = SUT.mapToEntity(airport)
        checkResult(result, airport)
    }


    private fun checkResult(airportEntity: AirportEntity, result: Airport) {
        assertThat(result.carriers, `is`(airportEntity.carriers))
        assertThat(result.city, `is`(airportEntity.city))
        assertThat(result.code, `is`(airportEntity.code))
        assertThat(result.country, `is`(airportEntity.country))
        assertThat(result.directFlights, `is`(airportEntity.directFlights))
        assertThat(result.elev, `is`(airportEntity.elev))
        assertThat(result.email, `is`(airportEntity.email))
        assertThat(result.icao, `is`(airportEntity.icao))
        assertThat(result.lat, `is`(airportEntity.lat))
        assertThat(result.lon, `is`(airportEntity.lon))
        assertThat(result.name, `is`(airportEntity.name))
        assertThat(result.phone, `is`(airportEntity.phone))
        assertThat(result.runwayLength, `is`(airportEntity.runwayLength))
        assertThat(result.state, `is`(airportEntity.state))
        assertThat(result.type, `is`(airportEntity.type))
        assertThat(result.tz, `is`(airportEntity.tz))
        assertThat(result.url, `is`(airportEntity.url))
        assertThat(result.woeid, `is`(airportEntity.woeid))
    }
}