package com.mobigod.avin.di

import android.app.Application
import android.content.Context
import com.mobigod.avin.BuildConfig
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
    @Singleton
    fun provideAppContext(context: Application): Context = context


    @Provides
    @Singleton
    @Named("base_url")
    fun provideBaseUrl() = BuildConfig.LUFTHANSA_SECRET


}