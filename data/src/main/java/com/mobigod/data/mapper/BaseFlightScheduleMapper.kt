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
    fun mapFromFlightEntity(entity: List<FlightEntity>): List<Flight> {
        return entity.map {
            flightEntity ->
            Flight(mapFromArrivalEntity(flightEntity.ArrivalEntity), mapFromDepartureEntity(flightEntity.DepartureEntity))
        }
    }

    fun mapToFlightEntity(flight: List<Flight>): List<FlightEntity> {
        return flight.map {
            flightDomain -> FlightEntity(mapToArrivalEntity(flightDomain.arrival), mapToDepartureEntity(flightDomain.departure))
        }
    }


    /**
     * Maps the arrival DS
     */
    private fun mapFromArrivalEntity(entity: ArrivalEntity): Arrival {
        return Arrival(entity.AirportCode, mapFromScheduledTimeLocalEntity(entity.ScheduledTimeLocalEntity))
    }

    private fun mapToArrivalEntity(arrival: Arrival): ArrivalEntity {
        return ArrivalEntity(arrival.airportCode, mapToScheduledTimeLocalEntity(arrival.scheduledTimeLocal))
    }


    /**
     * Maps departure DS
     */
    private fun mapFromDepartureEntity(entity: DepartureEntity): Departure {
        return Departure(entity.AirportCode, mapFromScheduledTimeLocalXEntity(entity.scheduledTimeLocalEntity))
    }

    private fun mapToDepartureEntity(domain: Departure): DepartureEntity {
        return DepartureEntity(domain.airportCode, mapToScheduledTimeLocalXEntity(domain.scheduledTimeLocal))
    }


    /**
     * Maps ScheduledTimeLocal DS
     */
    private fun mapFromScheduledTimeLocalEntity(entity: ScheduledTimeLocalEntity): ScheduledTimeLocal {
        return ScheduledTimeLocal(entity.DateTime)
    }

    private fun mapToScheduledTimeLocalEntity(domain: ScheduledTimeLocal): ScheduledTimeLocalEntity {
        return ScheduledTimeLocalEntity(domain.dateTime)
    }


    /**
     * Maps ScheduledTimeLocalX DS
     */
    private fun mapFromScheduledTimeLocalXEntity(entity: ScheduledTimeLocalXEntity): ScheduledTimeLocalX {
        return ScheduledTimeLocalX(entity.DateTime)
    }

    private fun mapToScheduledTimeLocalXEntity(domain: ScheduledTimeLocalX): ScheduledTimeLocalXEntity {
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