package com.mobigod.avin.di.modules

import android.content.Context
import com.mobigod.avin.di.scopes.ApplicationScope
import com.mobigod.avin.mapper.FlightScheduleViewMapper
import com.mobigod.data.mapper.AirportMapper
import com.mobigod.data.mapper.BaseMapper
import com.mobigod.data.mapper.FlightScheduleEntityMapper
import com.mobigod.data.mapper.TokenMapper
import com.mobigod.data.models.airport.AirportEntity
import com.mobigod.data.repositories.airport.AirportsRepository
import com.mobigod.data.repositories.airport.IAirportCache
import com.mobigod.data.repositories.airport.IAirportRemote
import com.mobigod.data.repositories.auth.AuthRepository
import com.mobigod.data.repositories.auth.IAuthCache
import com.mobigod.data.repositories.auth.IAuthRemote
import com.mobigod.data.repositories.schedule.FlightScheduleRepository
import com.mobigod.data.repositories.schedule.IFlightScheduleRemote
import com.mobigod.domain.entities.airport.Airport
import com.mobigod.domain.repository.IAirportsRepository
import com.mobigod.domain.repository.IAuthRepository
import com.mobigod.domain.repository.IFlightSchedulesRepository
import com.mobigod.remote.airport.AirportRemoteImpl
import com.mobigod.remote.airport.AirportService
import com.mobigod.remote.ApiService
import com.mobigod.remote.airport.AirportServiceImpl
import com.mobigod.remote.auth.AuthRemoteImpl
import com.mobigod.remote.schedule.FlightScheduleRemoteImpl
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
    fun provideTokenMapper() = TokenMapper()

    @Provides
    @ApplicationScope
    fun provideFlightScheduleViewMapper() = FlightScheduleViewMapper()


    @Provides
    @ApplicationScope
    fun provideFlightScheduleEntityMapper() = FlightScheduleEntityMapper()


    @Provides
    @ApplicationScope
    fun provideFlightScheduleRemoteImpl(apiService: ApiService): IFlightScheduleRemote
            = FlightScheduleRemoteImpl(apiService)


    @Provides
    @ApplicationScope
    fun provideFlightScheduleRepository(remote:IFlightScheduleRemote, mapper: FlightScheduleEntityMapper): IFlightSchedulesRepository
            = FlightScheduleRepository(remote, mapper)


    @Provides
    @ApplicationScope
    fun provideAirportService(context: Context): AirportService =
        AirportServiceImpl(context)


    @Provides
    @ApplicationScope
    fun provideAirportRemote(apiService: ApiService, airportService: AirportService, airportMapper: AirportMapper):IAirportRemote
     = AirportRemoteImpl(apiService, airportService, airportMapper)

    @Provides
    @ApplicationScope
    fun provideAirportRepository(remote: IAirportRemote, cache: IAirportCache, mapper: AirportMapper): IAirportsRepository
    = AirportsRepository(remote, cache, mapper)

    @Provides
    @ApplicationScope
    fun provideAuthRemote(apiService: ApiService): IAuthRemote
    = AuthRemoteImpl(apiService)

    @Provides
    @ApplicationScope
    fun provideAuthRepository(authCache: IAuthCache, authRemote: IAuthRemote, mapper: TokenMapper): IAuthRepository
    = AuthRepository(authCache, authRemote, mapper)

}