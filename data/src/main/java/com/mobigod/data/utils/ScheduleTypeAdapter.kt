package com.mobigod.data.utils

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.mobigod.data.models.airport.AirportEntity
import com.mobigod.data.models.schedules.ScheduleEntity
import com.mobigod.data.models.schedules.ScheduleResourceEntity
import java.lang.reflect.Type
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 09, 2020-02-09
//at: 11:25*/


@Deprecated("For some reason this doesn't work well", level = DeprecationLevel.ERROR)
class ScheduleTypeAdapter @Inject constructor(): TypeAdapter<ScheduleResourceEntity>() {
    private val gson = Gson()// so we don't have to write all our deserializers

    override fun write(out: JsonWriter?, value: ScheduleResourceEntity?) {
        gson.toJson(value, ScheduleResourceEntity::class.java, out)
    }

    override fun read(`in`: JsonReader?): ScheduleResourceEntity {
        val scheduleResourceEntity: ScheduleResourceEntity

        `in`?.beginObject()
        `in`?.nextName()

        if (`in`?.peek() == JsonToken.BEGIN_ARRAY) {
            val airportListType = object : TypeToken<List<ScheduleEntity>>() {}.type
            scheduleResourceEntity = ScheduleResourceEntity(gson.fromJson(`in`, airportListType))
        }else if (`in`?.peek() == JsonToken.BEGIN_OBJECT) {
            val scheduleType = object : TypeToken<ScheduleEntity>() {}.type
            val schduleEntity: ScheduleEntity = gson.fromJson(`in`, scheduleType)
            val scheduleEntities: List<ScheduleEntity> = listOf(schduleEntity)
            scheduleResourceEntity = ScheduleResourceEntity(scheduleEntities)


        }else {
            throw JsonParseException("Unexpected object " + `in`?.peek())
        }

        `in`.endObject()
        return scheduleResourceEntity
    }
}



/**
 * Some times schedules come as an object, sometimes as an array
 * This class handles such inconsistency
 */
class ScheduleTypeAdapter2 @Inject constructor(): JsonDeserializer<ScheduleResourceEntity> {

    var scheduleResourceEntity: ScheduleResourceEntity? = null

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ScheduleResourceEntity {
        json?.run {
            val schedules = this.asJsonObject.get("Schedule")
            if (schedules.isJsonArray) {
                val scheduleListType = object : TypeToken<List<ScheduleEntity>>() {}.type
                scheduleResourceEntity = ScheduleResourceEntity(context!!.deserialize(schedules, scheduleListType))
            }else if (schedules.isJsonObject){
                val scheduleType = object : TypeToken<ScheduleEntity>() {}.type
                val scheduleEntities: List<ScheduleEntity> = listOf(context!!.deserialize(schedules, scheduleType))
                scheduleResourceEntity = ScheduleResourceEntity(scheduleEntities)
            }else{
                throw JsonParseException("Invalid type")
            }
        }
        return scheduleResourceEntity!!
    }
}

