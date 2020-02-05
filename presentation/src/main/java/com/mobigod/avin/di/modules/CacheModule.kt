package com.mobigod.avin.di.modules

import android.content.Context
import com.mobigod.avin.di.scopes.ApplicationScope
import com.mobigod.avin.mapper.TokenViewMapper
import com.mobigod.cache.AirportCacheImpl
import com.mobigod.cache.db.AvionDatabase
import com.mobigod.cache.mapper.AirportEntityMapper
import com.mobigod.cache.mapper.CoreMapper
import com.mobigod.cache.mapper.TokenEntityMapper
import com.mobigod.cache.models.AirportDBEntity
import com.mobigod.cache.models.TokenPrefEntity
import com.mobigod.cache.preference.IPreferenceManager
import com.mobigod.cache.preference.PreferenceManager
import com.mobigod.cache.token.AuthCacheImpl
import com.mobigod.cache.token.ITokenManager
import com.mobigod.cache.token.TokenManager
import com.mobigod.cache.user.IUserManager
import com.mobigod.cache.user.UserManager
import com.mobigod.data.models.airport.AirportEntity
import com.mobigod.data.models.auth.TokenEntity
import com.mobigod.data.repositories.airport.IAirportCache
import com.mobigod.data.repositories.auth.IAuthCache
import dagger.Module
import dagger.Provides

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 16:58*/

@Module
class CacheModule {

    @Provides
    @ApplicationScope
    fun provideAirportEntityMapper(): CoreMapper<AirportDBEntity, AirportEntity> =  AirportEntityMapper()


    @Provides
    @ApplicationScope
    fun provideTokenEntityMapper() = TokenEntityMapper()

    @Provides
    @ApplicationScope
    fun provideTokenViewMapper() = TokenViewMapper()


    @Provides
    @ApplicationScope
    fun provideTokenManager(preferenceManager: PreferenceManager): ITokenManager = TokenManager(preferenceManager)

    @Provides
    @ApplicationScope
    fun providePreferenceManager(context: Context): IPreferenceManager = PreferenceManager(context)


    @Provides
    @ApplicationScope
    fun provideUserManager(preferenceManager: PreferenceManager): IUserManager = UserManager(preferenceManager)


    @Provides
    @ApplicationScope
    fun provideAuthCache(tokenManager: TokenManager, userManager: UserManager, mapper: TokenEntityMapper): IAuthCache
     = AuthCacheImpl(tokenManager, userManager, mapper)


    @Provides
    @ApplicationScope
    fun provideAirportCache(database: AvionDatabase, mapper: CoreMapper<AirportDBEntity, AirportEntity>, preferenceManager: PreferenceManager): IAirportCache
     = AirportCacheImpl(database, preferenceManager, mapper)


}