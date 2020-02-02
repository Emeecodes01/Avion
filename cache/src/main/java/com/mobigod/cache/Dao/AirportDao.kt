package com.mobigod.cache.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobigod.cache.models.AirportDBEntity
import io.reactivex.Completable
import io.reactivex.Single

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 20:56*/

@Dao
abstract class AirportDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)//Might not encounter any exeption sha
    abstract fun saveAirport(airportDBEntity: AirportDBEntity): Completable

    @Query("SELECT * FROM airports WHERE name LIKE :query OR state LIKE :query OR city LIKE :query")
    abstract fun searchForAirport(query: String): Single<List<AirportDBEntity>>

    @Query("SELECT * FROM airports WHERE code = :code")
    abstract fun getAirportByCode(code: String): Single<AirportDBEntity>
}