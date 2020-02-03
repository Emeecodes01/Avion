package com.mobigod.avin.di.scopes

import javax.inject.Scope

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 13:46*/


/**
 * Components and methods annotated with this are
 * Expected to last through out the lifecycle of the Activity
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope