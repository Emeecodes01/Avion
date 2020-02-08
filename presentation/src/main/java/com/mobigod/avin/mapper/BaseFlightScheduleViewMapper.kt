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
    protected fun mapFromFlightEntity(entity: FlightModel): Flight {
        return Flight(mapFromArrivalModel(entity.ArrivalModel), mapFromDepartureModel(entity.DepartureModel))
    }

    protected fun mapToFlightEntity(flight: Flight): FlightModel {
        return FlightModel(mapToArrivalModel(flight.arrival), mapToDepartureModel(flight.departure))
    }


    /**
     * Maps the arrival DS
     */
    private fun mapFromArrivalModel(entity: ArrivalModel): Arrival {
        return Arrival(entity.AirportCode, mapFromScheduledTimeLocalModel(entity.ScheduledTimeLocalModel), mapFromTerminalModel(entity.TerminalModel))
    }

    fun mapToArrivalModel(arrival: Arrival): ArrivalModel {
        return ArrivalModel(arrival.airportCode, mapToScheduledTimeLocalModel(arrival.scheduledTimeLocal), mapToTerminalModel(arrival.terminal))
    }


    /**
     * Maps TerminalModel DS
     */
    private fun mapFromTerminalModel(entity: TerminalModel): Terminal {
        return Terminal(entity.Name)
    }

    private fun mapToTerminalModel(domain: Terminal): TerminalModel {
        return TerminalModel(domain.name)
    }

    /**
     * Maps departure DS
     */
    private fun mapFromDepartureModel(entity: DepartureModel): Departure {
        return Departure(entity.AirportCode, mapFromScheduledTimeLocalXModel(entity.scheduledTimeLocalModel))
    }

    private fun mapToDepartureModel(domain: Departure): DepartureModel {
        return DepartureModel(domain.airportCode, mapToScheduledTimeLocalXModel(domain.scheduledTimeLocal))
    }


    /**
     * Maps ScheduledTimeLocalModel DS
     */
    private fun mapFromScheduledTimeLocalModel(model: ScheduledTimeLocalModel): ScheduledTimeLocal {
        return ScheduledTimeLocal(model.DateTime)
    }

    private fun mapToScheduledTimeLocalModel(domain: ScheduledTimeLocal): ScheduledTimeLocalModel {
        return ScheduledTimeLocalModel(domain.dateTime)
    }


    /**
     * Maps ScheduledTimeLocalXModel DS
     */
    private fun mapFromScheduledTimeLocalXModel(model: ScheduledTimeLocalXModel): ScheduledTimeLocalX {
        return ScheduledTimeLocalX(model.DateTime)
    }

    private fun mapToScheduledTimeLocalXModel(domain: ScheduledTimeLocalX): ScheduledTimeLocalXModel {
        return ScheduledTimeLocalXModel(domain.dateTime)
    }


    /**
     * Maps TotalJourneyModel DS
     */
    protected fun mapFromTotalJourneyModel(model: TotalJourneyModel): TotalJourney {
        return TotalJourney(model.Duration)
    }

    protected fun mapToTotalJourneyModel(domain: TotalJourney): TotalJourneyModel {
        return TotalJourneyModel(domain.duration)
    }
}