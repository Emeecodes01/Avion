package com.mobigod.avin.di.scopes

import javax.inject.Scope

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 16:19*/

/**
 * Objects should live only within fragment modules
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope