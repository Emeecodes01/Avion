package com.mobigod.cache

import android.os.Build
import androidx.room.Room
import com.mobigod.cache.db.AvionDatabase
import com.mobigod.cache.mapper.AirportEntityMapper
import com.mobigod.cache.mapper.CoreMapper
import com.mobigod.cache.models.AirportDBEntity
import com.mobigod.cache.preference.IPreferenceManager
import com.mobigod.cache.test.StubsGenerator
import com.mobigod.data.mapper.BaseMapper
import com.mobigod.data.models.airport.AirportEntity
import com.nhaarman.mockitokotlin2.any
import io.reactivex.Single
import konveyor.base.randomBuild
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.lang.AssertionError
import java.lang.IllegalStateException

/**
 * Created by: Emmanuel Ozibo
 * //on: 02, 2020-02-02
 * //at: 10:33
 */

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class AirportCacheImplTest {

    lateinit var db: AvionDatabase

    @Mock
    lateinit var prefmgr: IPreferenceManager


    @Mock
    lateinit var mapper: CoreMapper<AirportDBEntity, AirportEntity>

    lateinit var SUT: AirportCacheImpl

    private val mapNinja = AirportEntityMapper()


    private val AIRPORTS: List<AirportDBEntity> = listOf(
        AirportDBEntity("BNI","2", "Benin City", "Nigeria", "direct_flights",
            "258", "", "", "6.3161", "5.6", "Benin Airport", "", "7874",
            "Edo", "Airports", "Africa\\/Lagos", "", "12515060"),

        AirportDBEntity("BNI","2", "Benin City", "Nigeria", "direct_flights",
            "258", "", "", "6.3161", "5.6", "Benin Airport", "", "7874",
            "Edo", "Airports", "Africa\\/Lagos", "", "12515060"),

        AirportDBEntity("BNI","2", "Benin City", "Nigeria", "direct_flights",
            "258", "", "", "6.3161", "5.6", "Benin Airport", "", "7874",
            "Edo", "Airports", "Africa\\/Lagos", "", "12515060"))


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        db = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            AvionDatabase::class.java).allowMainThreadQueries().build()

        SUT = AirportCacheImpl(db, prefmgr, mapNinja)
    }

    @Test(expected = AssertionError::class)
    fun `searchForAirportWith user enters empty search query, throws an exception`() {
        val testObserver = SUT.searchForAirportWith("").test()
    }

    @Test(expected = AssertionError::class)
    fun `searchForAirportWith user enters a search query that is less than 3 characters, throws an exception`() {
        val testObserver = SUT.searchForAirportWith("lau").test()
    }

    @Test
    fun `searchForAirportWith return the list of airportEntities`() {
        val airportDBEntity: AirportDBEntity = randomBuild()
        val airportEntity: AirportEntity = randomBuild()

        val airportDBEntities = StubsGenerator.stubListOfSameObjectNthTimes(5, airportDBEntity)

        searchForAirportStubDbResponse(airportDBEntities)
        //searchForAirportMapperStub(mapNinja.mapFromDbEntity(airportDBEntity))

        val testObservable = SUT.searchForAirportWith("Lsgos").test()

        testObservable.assertValue(airportDBEntities.map { mapNinja.mapFromDbEntity(it) })

    }


    private fun searchForAirportStubDbResponse(airportsDBEntity: List<AirportDBEntity>){
        `when`(db.airportDao().searchForAirport(any()))
            .thenReturn(Single.just(airportsDBEntity))
    }


//    private fun searchForAirportMapperStub(airportEntity: AirportEntity) {
//        `when`(mapper.mapFromDbEntity(any()))
//            .thenReturn(airportEntity)
//    }


}