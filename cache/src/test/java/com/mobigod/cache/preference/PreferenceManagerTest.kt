package com.mobigod.cache.preference

import android.os.Build
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Created by: Emmanuel Ozibo
 * //on: 02, 2020-02-02
 * //at: 10:25
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class PreferenceManagerTest {

    lateinit var SUT: PreferenceManager

    @Before
    fun setUp() {
        val context = RuntimeEnvironment.application.applicationContext
        SUT = PreferenceManager(context)
    }


    @Test
    fun `isFirstRun can actually save a value`() {
        val isFirstRun = true
        SUT.isFirstRun = isFirstRun
        assertThat(SUT.isFirstRun, `is`(isFirstRun))
    }


}