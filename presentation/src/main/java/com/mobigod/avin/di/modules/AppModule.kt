package com.mobigod.avin.di.modules

import android.app.Application
import android.content.Context
import android.provider.DocumentsContract
import androidx.room.Room
import com.mobigod.avin.BuildConfig
import com.mobigod.avin.di.scopes.ApplicationScope
import com.mobigod.cache.db.AvionDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**Created by: Emmanuel Ozibo
//on: 02, 2020-02-02
//at: 15:43*/

@Module
class AppModule {

    @Provides
    @ApplicationScope
    fun provideAppContext(context: Application): Context = context



    @Provides
    @ApplicationScope
    fun provideAppDatabase(context: Context): AvionDatabase {
        return Room.databaseBuilder(context, AvionDatabase::class.java,
            "avion_cache.db")
            .build()
    }

}