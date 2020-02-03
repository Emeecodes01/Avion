package com.mobigod.avin.di.modules

import com.mobigod.avin.di.scopes.ApplicationScope
import com.mobigod.data.mapper.AirportMapper
import com.mobigod.remote.AirportRemoteImpl
import com.mobigod.remote.AirportService
import com.mobigod.remote.ApiService
import dagger.Module
import dagger.Provides

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 16:57*/

@Module
class RemoteModule {

    @Provides
    @ApplicationScope
    fun provideAirportRemoteMapper() = AirportMapper()


    @Provides
    @ApplicationScope
    fun provideAirportRemote(apiService: ApiService, airportService: AirportService, airportMapper: AirportMapper)
     = AirportRemoteImpl(apiService, airportService, airportMapper)

}