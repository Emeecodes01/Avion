package com.mobigod.cache.test

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mobigod.cache.models.AirportDBEntity
import com.mobigod.data.models.airport.AirportEntity
import java.io.IOException
import java.nio.charset.Charset

/**Created by: Emmanuel Ozibo
//on: 02, 2020-02-02
//at: 06:29*/
object TestUtils {
    private lateinit var context: Context

    private val AIRPORTS_JSON = """
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

    private var list: List<AirportDBEntity> = listOf()

    fun initTestObjects(context: Context) {
        this.context = context

        val gson = Gson()
        val airportListType = object : TypeToken<List<AirportDBEntity>>() {}.type

        list =  gson.fromJson(AIRPORTS_JSON, airportListType)
    }

    fun generate(index: Int): AirportDBEntity {

        return list[index]
    }

    fun getAirportList() = list
}