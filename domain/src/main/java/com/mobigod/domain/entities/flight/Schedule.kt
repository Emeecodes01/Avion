package com.mobigod.domain.entities.flight

data class Schedule(
    var flight: List<Flight>,
    var totalJourney: TotalJourney
)