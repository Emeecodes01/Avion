package com.mobigod.avin.models.airport

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**Created by: Emmanuel Ozibo
//on: 10, 2020-02-10
//at: 00:07*/


/**
 * I can do better with this naming sha, but this class holds the codes for both
 * departure and arrival airports
 */
@Parcelize
data class AirportCodesHolder (
    val departureAirportCode: String,
    val arrivalAirportCode: String
): Parcelable