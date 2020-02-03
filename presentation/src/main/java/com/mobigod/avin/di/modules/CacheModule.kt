package com.mobigod.avin.di.modules

import android.content.Context
import com.mobigod.avin.di.scopes.ApplicationScope
import com.mobigod.cache.AirportCacheImpl
import com.mobigod.cache.db.AvionDatabase
import com.mobigod.cache.mapper.AirportEntityMapper
import com.mobigod.cache.preference.PreferenceManager
import dagger.Module
import dagger.Provides

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 16:58*/

@Module
class CacheModule {

    @Provides
    @ApplicationScope
    fun provideAirportEntityMapper() =  AirportEntityMapper()

    @Provides
    @ApplicationScope
    fun providePreferenceManager(context: Context) = PreferenceManager(context)


    @Provides
    @ApplicationScope
    fun provideAirportCache(database: AvionDatabase, mapper: AirportEntityMapper, preferenceManager: PreferenceManager)
     = AirportCacheImpl(database, preferenceManager, mapper)

}