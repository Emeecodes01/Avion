package com.mobigod.avin.di

import android.content.Context
import com.mobigod.avin.AvionApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**Created by: Emmanuel Ozibo
//on: 02, 2020-02-02
//at: 15:43*/


@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityBuilder::class, AppModule::class])
interface AppComponent: AndroidInjector<AvionApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: AvionApplication): Builder

        fun build(): AppComponent
    }

}