package com.mobigod.remote

//import com.mobigod.domain.entities.flight.ScheduleResourceEntity
import com.mobigod.data.models.auth.TokenEntity
import com.mobigod.data.models.schedules.FlightScheduleResponse
import com.mobigod.data.models.schedules.ScheduleEntity
import com.mobigod.data.models.schedules.ScheduleResourceEntity
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 08:09*/

interface ApiService {

    @GET("operations/schedules/{origin}/{destination}/{fromDateTime}")
    fun getFlightSchedules(@Path("origin") origin: String, @Path("destination") destination: String,
                           @Path("fromDateTime") departureDate: String, @Query("directFlights") directFlights: Int = 1): Single<FlightScheduleResponse>


    @FormUrlEncoded
    @POST("oauth/token")
    fun loginUser(@Field("client_id") userKey: String, @Field("client_secret") userSecret: String,
                  @Field("grant_type") grantType: String): Single<TokenEntity>
}