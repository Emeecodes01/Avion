package com.mobigod.avin.di.modules

import com.mobigod.avin.di.scopes.ActivityScope
import com.mobigod.avin.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 04:53*/

@Module
abstract class ActivityBuilder {

    /**
     * Dagger generates a SubComponent for this activity class, and annotates it with
     * ActivityScope, this causes the provided modules to only live within the lifecycle
     * of this activity
     */
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun authActivityInjectorProvider(): AuthActivity




}