package com.mobigod.avin.di

import com.mobigod.avin.AvionApplication
import com.mobigod.avin.di.modules.ActivityBuilder
import com.mobigod.avin.di.modules.AppModule
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