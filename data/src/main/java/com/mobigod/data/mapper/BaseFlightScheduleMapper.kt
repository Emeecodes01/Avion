package com.mobigod.data.mapper

import com.mobigod.data.models.schedules.*
import com.mobigod.domain.entities.flight.*

/**Created by: Emmanuel Ozibo
//on: 08, 2020-02-08
//at: 01:06*/

abstract class BaseFlightScheduleMapper: BaseMapper<ScheduleEntity, Schedule> {


    /**
     * Maps the flight data structure
     */
    fun mapFromFlightEntity(entity: FlightEntity): Flight {
        return Flight(mapFromArrivalEntity(entity.ArrivalEntity), mapFromDepartureEntity(entity.DepartureEntity))
    }

    fun mapToFlightEntity(flight: Flight): FlightEntity {
        return FlightEntity(mapToArrivalEntity(flight.arrival), mapToDepartureEntity(flight.departure))
    }


    /**
     * Maps the arrival DS
     */
    fun mapFromArrivalEntity(entity: ArrivalEntity): Arrival {
        return Arrival(entity.AirportCode, mapFromScheduledTimeLocalEntity(entity.ScheduledTimeLocalEntity), mapFromTerminalEntity(entity.TerminalEntity))
    }

    fun mapToArrivalEntity(arrival: Arrival): ArrivalEntity {
        return ArrivalEntity(arrival.airportCode, mapToScheduledTimeLocalEntity(arrival.scheduledTimeLocal), mapToTerminalEntity(arrival.terminal))
    }


    /**
     * Maps Terminal DS
     */
    fun mapFromTerminalEntity(entity: TerminalEntity): Terminal {
        return Terminal(entity.Name)
    }

    fun mapToTerminalEntity(domain: Terminal): TerminalEntity {
        return TerminalEntity(domain.name)
    }

    /**
     * Maps departure DS
     */
    fun mapFromDepartureEntity(entity: DepartureEntity): Departure {
        return Departure(entity.AirportCode, mapFromScheduledTimeLocalXEntity(entity.scheduledTimeLocalEntity))
    }

    fun mapToDepartureEntity(domain: Departure): DepartureEntity {
        return DepartureEntity(domain.airportCode, mapToScheduledTimeLocalXEntity(domain.scheduledTimeLocal))
    }


    /**
     * Maps ScheduledTimeLocal DS
     */
    fun mapFromScheduledTimeLocalEntity(entity: ScheduledTimeLocalEntity): ScheduledTimeLocal {
        return ScheduledTimeLocal(entity.DateTime)
    }

    fun mapToScheduledTimeLocalEntity(domain: ScheduledTimeLocal): ScheduledTimeLocalEntity {
        return ScheduledTimeLocalEntity(domain.dateTime)
    }


    /**
     * Maps ScheduledTimeLocalX DS
     */
    fun mapFromScheduledTimeLocalXEntity(entity: ScheduledTimeLocalXEntity): ScheduledTimeLocalX {
        return ScheduledTimeLocalX(entity.DateTime)
    }

    fun mapToScheduledTimeLocalXEntity(domain: ScheduledTimeLocalX): ScheduledTimeLocalXEntity {
        return ScheduledTimeLocalXEntity(domain.dateTime)
    }


    /**
     * Maps TotalJourney DS
     */
    fun mapFromTotalJourneyEntity(entity: TotalJourneyEntity): TotalJourney {
        return TotalJourney(entity.Duration)
    }

    fun mapToTotalJourneyEntity(domain: TotalJourney): TotalJourneyEntity {
        return TotalJourneyEntity(domain.duration)
    }
}