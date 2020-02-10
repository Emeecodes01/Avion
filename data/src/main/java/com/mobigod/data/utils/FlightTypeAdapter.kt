package com.mobigod.data.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import com.mobigod.data.models.schedules.FlightEntity
import com.mobigod.data.models.schedules.ScheduleEntity
import com.mobigod.data.models.schedules.ScheduleResourceEntity
import com.mobigod.data.models.schedules.TotalJourneyEntity
import java.lang.reflect.Type

/**Created by: Emmanuel Ozibo
//on: 09, 2020-02-09
//at: 19:00*/

/**
 * Created to handle inconsistency in flight data object,
 * sometimes the server return list sometimes object
 */
class FlightTypeAdapter: JsonDeserializer<ScheduleEntity> {

    var scheduleResourceEntity: ScheduleEntity? = null

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ScheduleEntity {
        json?.run {
            val flights = this.asJsonObject.get("Flight")
            val totalJourneyEntity = this.asJsonObject.get("TotalJourney")

            val totalJourneyType = object : TypeToken<TotalJourneyEntity>() {}.type

            when {
                flights.isJsonArray -> {
                    val flightListType = object : TypeToken<List<FlightEntity>>() {}.type

                    scheduleResourceEntity = ScheduleEntity(
                        context!!.deserialize(flights, flightListType),
                        context.deserialize(totalJourneyEntity, totalJourneyType)
                    )

                }
                flights.isJsonObject -> {
                    val scheduleType = object : TypeToken<FlightEntity>() {}.type

                    val scheduleEntities: List<FlightEntity> =
                        listOf(context!!.deserialize(flights, scheduleType))
                    scheduleResourceEntity = ScheduleEntity(
                        scheduleEntities,
                        context.deserialize(totalJourneyEntity, totalJourneyType)
                    )

                }
                else -> throw JsonParseException("Invalid type")
            }
        }
        return scheduleResourceEntity!!

    }
}