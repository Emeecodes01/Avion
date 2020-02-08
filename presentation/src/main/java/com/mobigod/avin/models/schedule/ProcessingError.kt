package com.mobigod.avin.models.schedule

data class ProcessingError(
    var RetryIndicator: Boolean,
    var Code: String,
    var Description: String,
    var InfoURL: String,
    var Type: String
)