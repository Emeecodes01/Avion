package com.mobigod.avin.mapper

import com.mobigod.avin.models.schedule.*
import com.mobigod.avin.models.schedule.ScheduledTimeLocalXModel
import com.mobigod.avin.models.schedule.TotalJourneyModel
import com.mobigod.domain.entities.flight.*

/**Created by: Emmanuel Ozibo
//on: 08, 2020-02-08
//at: 02:19*/

abstract class BaseFlightScheduleViewMapper: BaseViewMapper<ScheduleModel, Schedule>{


    /**
     * Maps the flight data structure
     */
    fun mapFromFlightEntity(entity: FlightModel): Flight {
        return Flight(mapFromArrivalModel(entity.ArrivalModel), mapFromDepartureModel(entity.DepartureModel))
    }

    fun mapToFlightEntity(flight: Flight): FlightModel {
        return FlightModel(mapToArrivalModel(flight.arrival), mapToDepartureModel(flight.departure))
    }


    /**
     * Maps the arrival DS
     */
    fun mapFromArrivalModel(entity: ArrivalModel): Arrival {
        return Arrival(entity.AirportCode, mapFromScheduledTimeLocalModel(entity.ScheduledTimeLocalModel), mapFromTerminalModel(entity.TerminalModel))
    }

    fun mapToArrivalModel(arrival: Arrival): ArrivalModel {
        return ArrivalModel(arrival.airportCode, mapToScheduledTimeLocalModel(arrival.scheduledTimeLocal), mapToTerminalModel(arrival.terminal))
    }


    /**
     * Maps TerminalModel DS
     */
    fun mapFromTerminalModel(entity: TerminalModel): Terminal {
        return Terminal(entity.Name)
    }

    fun mapToTerminalModel(domain: Terminal): TerminalModel {
        return TerminalModel(domain.name)
    }

    /**
     * Maps departure DS
     */
    fun mapFromDepartureModel(entity: DepartureModel): Departure {
        return Departure(entity.AirportCode, mapFromScheduledTimeLocalXModel(entity.scheduledTimeLocalModel))
    }

    fun mapToDepartureModel(domain: Departure): DepartureModel {
        return DepartureModel(domain.airportCode, mapToScheduledTimeLocalXModel(domain.scheduledTimeLocal))
    }


    /**
     * Maps ScheduledTimeLocalModel DS
     */
    fun mapFromScheduledTimeLocalModel(model: ScheduledTimeLocalModel): ScheduledTimeLocal {
        return ScheduledTimeLocal(model.DateTime)
    }

    fun mapToScheduledTimeLocalModel(domain: ScheduledTimeLocal): ScheduledTimeLocalModel {
        return ScheduledTimeLocalModel(domain.dateTime)
    }


    /**
     * Maps ScheduledTimeLocalXModel DS
     */
    fun mapFromScheduledTimeLocalXModel(model: ScheduledTimeLocalXModel): ScheduledTimeLocalX {
        return ScheduledTimeLocalX(model.DateTime)
    }

    fun mapToScheduledTimeLocalXModel(domain: ScheduledTimeLocalX): ScheduledTimeLocalXModel {
        return ScheduledTimeLocalXModel(domain.dateTime)
    }


    /**
     * Maps TotalJourneyModel DS
     */
    fun mapFromTotalJourneyModel(model: TotalJourneyModel): TotalJourney {
        return TotalJourney(model.Duration)
    }

    fun mapToTotalJourneyModel(domain: TotalJourney): TotalJourneyModel {
        return TotalJourneyModel(domain.duration)
    }
}