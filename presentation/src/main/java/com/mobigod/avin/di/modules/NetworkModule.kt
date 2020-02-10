package com.mobigod.avin.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mobigod.avin.BuildConfig
import com.mobigod.avin.di.scopes.ApplicationScope
import com.mobigod.avin.utils.network.AuthInterceptor
import com.mobigod.data.models.schedules.FlightEntity
import com.mobigod.data.models.schedules.ScheduleEntity
import com.mobigod.data.models.schedules.ScheduleResourceEntity
import com.mobigod.data.repositories.auth.IAuthCache
import com.mobigod.data.utils.FlightTypeAdapter
import com.mobigod.data.utils.ScheduleTypeAdapter2
import com.mobigod.remote.airport.AirportServiceImpl
import com.mobigod.remote.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**Created by: Emmanuel Ozibo
//on: 02, 2020-02-02
//at: 15:44*/

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideOkHttpLoggerInterceptor() = HttpLoggingInterceptor().apply {
        level  = HttpLoggingInterceptor.Level.BASIC
    }


    @Provides
    @ApplicationScope
    fun provideScheduleTypeAdapter() = ScheduleTypeAdapter2()


    @Provides
    @ApplicationScope
    fun provideFlightTypeAdapter() = FlightTypeAdapter()


    @Provides
    @ApplicationScope
    fun provideGson(scheduleTypeAdapter: ScheduleTypeAdapter2, flightTypeAdapter: FlightTypeAdapter): Gson {
        return  GsonBuilder()
            .registerTypeAdapter(ScheduleResourceEntity::class.java, scheduleTypeAdapter)
            .registerTypeAdapter(ScheduleEntity::class.java, flightTypeAdapter)
            .create()
    }


    @Provides
    @ApplicationScope
    fun provideCache(context: Context): Cache {
        val cacheSize = 5 * 1024 * 1024L // 5 MB
        return Cache(context.cacheDir, cacheSize)
    }


    @Provides
    @ApplicationScope
    fun provideAuthInterceptor(authCache: IAuthCache) = AuthInterceptor(authCache)


    @Provides
    @ApplicationScope
    fun provideOkhttp(authInterceptor: AuthInterceptor, cache: Cache, logging: HttpLoggingInterceptor /*authInterceptor: AuthInterceptor*/):
            OkHttpClient {
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(logging)
            .addInterceptor(authInterceptor)
            .cache(cache)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()

        return client
    }



    @Provides
    @ApplicationScope
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        return builder.build()
    }



    @Provides
    @ApplicationScope
    fun provideApiService(retrofit: Retrofit)
            = retrofit.create(ApiService::class.java)

}