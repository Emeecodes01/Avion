package com.mobigod.cache.Dao

import android.os.Build
import androidx.room.Room
import com.mobigod.cache.db.AvionDatabase
import com.mobigod.cache.models.AirportDBEntity
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.AssertionError

/**
 * Created by: Emmanuel Ozibo
 * //on: 02, 2020-02-02
 * //at: 09:29
 */

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class AirportDaoTest {


    lateinit var SUT: AvionDatabase

    val AIRPORTS: List<AirportDBEntity> = listOf(
        AirportDBEntity("BNI","Benin City", "BNI", "Nigeria", "direct_flights",
            "258", "", "", "6.3161", "5.6", "Benin Airport", "", "7874",
            "Edo", "Airports", "Africa\\/Lagos", "", "12515060"),

        AirportDBEntity("LOS","Ikeja", "LOS", "Nigeria", "42",
            "258", "", "", "6.3161", "5.6", "Lagos Murtala Muhammed Airport", "", "7874",
            "Lagos", "Airports", "Africa\\/Lagos", "", "12515060"),

        AirportDBEntity("ABV","Gwagwa", "ABV", "Nigeria", "direct_flights",
            "258", "", "", "6.3161", "5.6", "Abuja International Airport", "", "7874",
            "Abuja Capital Territory", "Airports", "Africa\\/Lagos", "", "12515060"))

    @Before
    fun setUp() {
        val context = RuntimeEnvironment.application.applicationContext
        SUT = Room.inMemoryDatabaseBuilder(context, AvionDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun tearDown() {
        SUT.close()
    }

    @Test
    fun `saveAirport saves the correct value`() {
        val airport = AIRPORTS[0]
        val test = SUT.airportDao().saveAirport(airport).test()
        test.assertComplete()
    }


    @Test
    fun `getAirportByCode returns the correct airport`() {
        val airport = AIRPORTS[0]
        val test = SUT.airportDao().saveAirport(airport)
            .test()
        test.assertComplete()

        val testObserver = SUT.airportDao().getAirportByCode("BNI").test()
        testObserver.assertValue(airport)
    }



    @Test
    fun `searchForAirport returns airports that matches a valid query`() {
        setUpDbInsert()
        val testObserver = SUT.airportDao().searchForAirport("%Lago%").test()
        testObserver.assertValue {
            value ->
            value.isNotEmpty()
        }
    }


    @Test(expected = AssertionError::class)
    fun `searchForAirport returns airports that matches an invalid query`() {
        setUpDbInsert()
        val testObserver = SUT.airportDao().searchForAirport("%Aqiq%").test()
        testObserver.assertValue {
                value ->
            value.isNotEmpty()
        }
    }



    private fun setUpDbInsert() {
        SUT.airportDao().saveAirport(AIRPORTS[0]).test().assertComplete()
        SUT.airportDao().saveAirport(AIRPORTS[1]).test().assertComplete()
        SUT.airportDao().saveAirport(AIRPORTS[2]).test().assertComplete()
    }
}