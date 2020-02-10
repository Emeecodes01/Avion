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
    protected fun mapFromFlightEntity(entity: List<FlightModel>): List<Flight> {
        return entity.map {
            flightModel ->
            Flight(mapFromArrivalModel(flightModel.ArrivalModel), mapFromDepartureModel(flightModel.DepartureModel))
        }
    }

    protected fun mapToFlightEntity(flight: List<Flight>): List<FlightModel> {
        return flight.map {
            domainFlight ->
            FlightModel(mapToArrivalModel(domainFlight.arrival), mapToDepartureModel(domainFlight.departure))
        }
    }


    /**
     * Maps the arrival DS
     */
    private fun mapFromArrivalModel(entity: ArrivalModel): Arrival {
        return Arrival(entity.AirportCode, mapFromScheduledTimeLocalModel(entity.ScheduledTimeLocalModel))
    }

    fun mapToArrivalModel(arrival: Arrival): ArrivalModel {
        return ArrivalModel(arrival.airportCode, mapToScheduledTimeLocalModel(arrival.scheduledTimeLocal))
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