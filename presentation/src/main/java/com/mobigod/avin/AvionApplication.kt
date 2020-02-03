package com.mobigod.avin

import com.mobigod.avin.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**Created by: Emmanuel Ozibo
//on: 02, 2020-02-02
//at: 15:39*/


class AvionApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

    }


}