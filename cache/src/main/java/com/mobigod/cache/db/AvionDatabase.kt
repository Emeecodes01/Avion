package com.mobigod.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobigod.cache.Dao.AirportDao
import com.mobigod.cache.models.AirportDBEntity

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 20:55*/

@Database(entities = [AirportDBEntity::class], version = 1, exportSchema = false)
abstract class AvionDatabase: RoomDatabase() {

    abstract fun airportDao(): AirportDao

    private var instance: AvionDatabase? = null

    private val lock = Any()

    fun getInstance(context: Context): AvionDatabase {
        if (instance == null) {
            synchronized(lock) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        AvionDatabase::class.java, "avion.db")
                        .build()
                }
                return instance!!
            }
        }
        return instance!!
    }

}