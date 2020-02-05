package com.mobigod.remote

//import com.mobigod.domain.entities.flight.ScheduleResource
import com.mobigod.data.models.auth.TokenEntity
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

/**Created by: Emmanuel Ozibo
//on: 01, 2020-02-01
//at: 08:09*/

interface ApiService {

    /*@GET("v1/operations/schedules/{origin}/{destination}/{fromDateTime}")
    fun getFlightSchedules(@Path("origin") origin: String, @Path("destination") destination: String,
                           @Path("fromDateTime") departureDate: String): Single<ScheduleResource>*/

    @FormUrlEncoded
    @POST("oauth/token")
    fun loginUser(@Field("client_id") userKey: String, @Field("client_secret") userSecret: String,
                  @Field("grant_type") grantType: String): Single<TokenEntity>



    @POST("oauth/token")
    fun loginUserSync(@Query("client_id") userKey: String, @Query("client_secret") userSecret: String,
                  @Query("grant_type") grantType: String): Call<TokenEntity>
}