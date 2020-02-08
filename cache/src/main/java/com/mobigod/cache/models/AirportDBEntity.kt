package com.mobigod.cache.models

import androidx.room.*

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 19:59*/

/**
 * I am loading up all 4k+ airports into the database, to enhance search, i have to
 * index some columns that the user is likely to search for, in this case i choose [name, city, state],
 * as my index
 */
@Entity(tableName = "airports", primaryKeys = ["code"],
    indices = [Index("name", "city", "state")])
//@Fts4
data class AirportDBEntity(
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "rowid")
//    val rowId: Int,//decaring this to support full text search
    var carriers: String?,
    var city: String?,
    var code: String,
    var country: String?,
    var directFlights: String?,
    var elev: String?,
    var email: String?,
    var icao: String?,
    var lat: String?,
    var lon: String?,
    var name: String?,
    var phone: String?,
    var runwayLength: String?,
    var state: String?,
    var type: String?,
    var tz: String?,
    var url: String?,
    var woeid: String?
)