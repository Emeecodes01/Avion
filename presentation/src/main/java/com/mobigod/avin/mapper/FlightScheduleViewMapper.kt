package com.mobigod.avin.mapper

import com.mobigod.avin.models.schedule.ScheduleModel
import com.mobigod.domain.entities.flight.Schedule

/**Created by: Emmanuel Ozibo
//on: 08, 2020-02-08
//at: 02:50*/
class FlightScheduleViewMapper: BaseFlightScheduleViewMapper() {
    override fun mapFromViewModel(par: ScheduleModel): Schedule {
        return Schedule(mapFromFlightEntity(par.FlightModel), mapFromTotalJourneyModel(par.TotalJourneyModel))
    }

    override fun mapToViewModel(par: Schedule): ScheduleModel {
        return ScheduleModel(mapToFlightEntity(par.flight), mapToTotalJourneyModel(par.totalJourney))
    }
}