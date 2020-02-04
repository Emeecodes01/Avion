package com.mobigod.remote

//import com.mobigod.domain.entities.flight.ScheduleResource
import com.mobigod.data.models.auth.TokenEntity
import com.mobigod.domain.entities.token.Token
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 08:09*/

interface ApiService {

    /*@GET("v1/operations/schedules/{origin}/{destination}/{fromDateTime}")
    fun getFlightSchedules(@Path("origin") origin: String, @Path("destination") destination: String,
                           @Path("fromDateTime") departureDate: String): Single<ScheduleResource>*/

    @POST("oauth/token")
    fun loginUser(@Query("client_id") userKey: String, @Query("client_secret") userSecret: String,
                  @Query("grant_type") grantType: String): Single<TokenEntity>



    @POST("oauth/token")
    fun loginUserSync(@Query("client_id") userKey: String, @Query("client_secret") userSecret: String,
                  @Query("grant_type") grantType: String): Call<TokenEntity>
}