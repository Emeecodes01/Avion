package com.mobigod.cache.mapper

import com.mobigod.cache.models.AirportDBEntity
import com.mobigod.cache.test.StubsGenerator
import com.mobigod.data.models.airport.AirportEntity
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by: Emmanuel Ozibo
 * //on: 01, 2020-02-01
 * //at: 21:11
 */

@RunWith(MockitoJUnitRunner::class)
class AirportEntityMapperTest {

    lateinit var SUT: AirportEntityMapper

    @Mock
    lateinit var mapper: CoreMapper<AirportDBEntity, AirportEntity>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        SUT = AirportEntityMapper()
    }


    @Test
    fun `mapFromDbEntity returns correct AirportEntity`() {
        val airportDBEntity = StubsGenerator.stubAirportDbEntity()
        val airportEntity = SUT.mapFromDbEntity(airportDBEntity)
        assertDataEquality(airportDBEntity, airportEntity)
    }


    @Test
    fun `mapToDbEntity returns correct AirportDbEntity`() {
        val airportEntity = StubsGenerator.stubAirportEntity()
        val airportDBEntity = SUT.mapToDbEntity(airportEntity)
        assertDataEquality(airportDBEntity, airportEntity)
    }


    private fun assertDataEquality(airportDBEntity: AirportDBEntity, airportEntity: AirportEntity) {
        assertThat(airportDBEntity.carriers, `is`(airportEntity.carriers))
        assertThat(airportDBEntity.city, `is`(airportEntity.city))
        assertThat(airportDBEntity.code, `is`(airportEntity.code))
        assertThat(airportDBEntity.country, `is`(airportEntity.country))
        assertThat(airportDBEntity.directFlights, `is`(airportEntity.directFlights))
        assertThat(airportDBEntity.elev, `is`(airportEntity.elev))
        assertThat(airportDBEntity.email, `is`(airportEntity.email))
        assertThat(airportDBEntity.icao, `is`(airportEntity.icao))
        assertThat(airportDBEntity.lat, `is`(airportEntity.lat))
        assertThat(airportDBEntity.lon, `is`(airportEntity.lon))
        assertThat(airportDBEntity.name, `is`(airportEntity.name))
        assertThat(airportDBEntity.phone, `is`(airportEntity.phone))
        assertThat(airportDBEntity.runwayLength, `is`(airportEntity.runwayLength))
        assertThat(airportDBEntity.state, `is`(airportEntity.state))
        assertThat(airportDBEntity.type, `is`(airportEntity.type))
        assertThat(airportDBEntity.tz, `is`(airportEntity.tz))
        assertThat(airportDBEntity.url, `is`(airportEntity.url))
        assertThat(airportDBEntity.woeid, `is`(airportEntity.woeid))
    }

}