package com.mobigod.remote

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.*
import com.mobigod.remote.test.StubGenerator
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Assert
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Created by: Emmanuel Ozibo
 * //on: 01, 2020-02-01
 * //at: 11:57
 */

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class AirportServiceImplTest {

    /**
     * SUT => SYSTEM UNDER TEST
     */
    lateinit var SUT: AirportServiceImpl

    private val SAMPLE_TEST_JSON_STRING = """
[
  {
    "code": "BNI",
    "lat": "6.3161",
    "lon": "5.6",
    "name": "Benin Airport",
    "city": "Benin City",
    "state": "Edo",
    "country": "Nigeria",
    "woeid": "12515060",
    "tz": "Africa\/Lagos",
    "phone": "",
    "type": "Airports",
    "email": "",
    "url": "",
    "runway_length": "7874",
    "elev": "258",
    "icao": "",
    "direct_flights": "1",
    "carriers": "2"
  },

  {
    "code": "LOS",
    "lat": "6.575",
    "lon": "3.3222",
    "name": "Lagos Murtala Muhammed Airport",
    "city": "Ikeja",
    "state": "Lagos",
    "country": "Nigeria",
    "woeid": "12515073",
    "tz": "Africa\/Lagos",
    "phone": "",
    "type": "Airports",
    "email": "",
    "url": "",
    "runway_length": "12795",
    "elev": "135",
    "icao": "DNMM",
    "direct_flights": "42",
    "carriers": "37"
  },

  {
    "code": "ABV",
    "lat": "9.0056",
    "lon": "7.2661",
    "name": "Abuja International Airport",
    "city": "Gwagwa",
    "state": "Abuja Capital Territory",
    "country": "Nigeria",
    "woeid": "12515056",
    "tz": "Africa\/Lagos",
    "phone": "",
    "type": "Airports",
    "email": "",
    "url": "",
    "runway_length": "11808",
    "elev": "1122",
    "icao": "DNAA",
    "direct_flights": "9",
    "carriers": "12"
  }
]
    """.trim()

    @Before
    fun setUp() {
        val applicationContext = getApplicationContext<Application>()
        SUT = AirportServiceImpl(applicationContext)
    }


    @Test
    fun `getAirports returns lists of airport entities data `(){
        //ARRANGE
        val fileName = "test_airport.json"
        val expected = StubGenerator.generateListOfAirportEntities(SAMPLE_TEST_JSON_STRING)
        //ACT
        val result = SUT.getAirports(fileName)
        //ASSERT
        assertThat(expected, `is`(result))
    }



}