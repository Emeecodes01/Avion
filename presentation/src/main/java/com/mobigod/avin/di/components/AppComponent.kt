package com.mobigod.avin.di.components

import com.mobigod.avin.AvionApplication
import com.mobigod.avin.di.modules.*
import com.mobigod.avin.di.scopes.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

/**Created by: Emmanuel Ozibo
//on: 02, 2020-02-02
//at: 15:43*/


@ApplicationScope
@Component(modules = [AndroidInjectionModule::class, ActivityBuilder::class, AppModule::class,
CacheModule::class, NetworkModule::class, RemoteModule::class, ViewModelModule::class])
interface AppComponent: AndroidInjector<AvionApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: AvionApplication): Builder

        fun build(): AppComponent
    }

}