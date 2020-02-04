package com.mobigod.avin.di.modules

import android.content.Context
import com.mobigod.avin.di.scopes.ApplicationScope
import com.mobigod.data.mapper.AirportMapper
import com.mobigod.data.mapper.BaseMapper
import com.mobigod.data.models.airport.AirportEntity
import com.mobigod.domain.entities.airport.Airport
import com.mobigod.remote.airport.AirportRemoteImpl
import com.mobigod.remote.airport.AirportService
import com.mobigod.remote.ApiService
import com.mobigod.remote.airport.AirportServiceImpl
import com.mobigod.remote.auth.AuthRemoteImpl
import dagger.Module
import dagger.Provides

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 16:57*/

@Module
class RemoteModule {

    @Provides
    @ApplicationScope
    fun provideAirportRemoteMapper(): BaseMapper<AirportEntity, Airport> = AirportMapper()


    @Provides
    @ApplicationScope
    fun provideAirportService(context: Context): AirportService =
        AirportServiceImpl(context)


    @Provides
    @ApplicationScope
    fun provideAirportRemote(apiService: ApiService, airportService: AirportService, airportMapper: AirportMapper)
     = AirportRemoteImpl(apiService, airportService, airportMapper)


    @Provides
    @ApplicationScope
    fun provideAuthRemote(apiService: ApiService)
    = AuthRemoteImpl(apiService)

}