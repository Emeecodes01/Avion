package com.mobigod.data.mapper

import com.mobigod.data.models.schedules.ScheduleEntity
import com.mobigod.data.models.schedules.ScheduleResourceEntity
import com.mobigod.domain.entities.flight.Schedule
import com.mobigod.domain.entities.flight.ScheduleResource

/**Created by: Emmanuel Ozibo
//on: 07, 2020-02-07
//at: 23:51*/
class FlightScheduleEntityMapper: BaseFlightScheduleMapper() {
    override fun mapFromEntity(entity: ScheduleEntity): Schedule {
        return Schedule(mapFromFlightEntity(entity.FlightEntity), mapFromTotalJourneyEntity(entity.TotalJourneyEntity))
    }

    override fun mapToEntity(domain: Schedule): ScheduleEntity {
        return ScheduleEntity(mapToFlightEntity(domain.flight), mapToTotalJourneyEntity(domain.totalJourney))
    }

}