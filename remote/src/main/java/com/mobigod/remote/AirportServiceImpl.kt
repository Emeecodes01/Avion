package com.mobigod.remote

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mobigod.data.models.airport.AirportEntity
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.nio.charset.Charset
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 10:26*/
class AirportServiceImpl @Inject constructor(private val context: Context): AirportService {

    private val TAG = "AirPortServiceImpl"


    override fun getAirports(file: String): List<AirportEntity> {
        val airportJson = openJSONFromAssert(file)
        val gson = Gson()
        val airportListType = object : TypeToken<List<AirportEntity>>() {}.type

        return gson.fromJson(airportJson, airportListType)
    }




    private fun openJSONFromAssert(fileName: String): String? {
        var json: String? = null
        try {
            val `is` = context.assets.open(fileName)

            val size = `is`.available()

            val buffer = ByteArray(size)

            `is`.read(buffer)

            `is`.close()

            json = String(buffer, Charset.forName("UTF-8"))


        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json.trim()
    }


}