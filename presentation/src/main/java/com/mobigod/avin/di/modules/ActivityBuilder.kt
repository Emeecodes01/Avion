package com.mobigod.avin.di.modules

import com.mobigod.avin.features.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 04:53*/

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun authActivityInjectorProvider(): AuthActivity
}