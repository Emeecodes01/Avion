package com.mobigod.avin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mobigod.avin.ui.auth.AuthActivity
import com.mobigod.avin.ui.flights.FlightSchedulesActivity
import com.mobigod.avin.ui.setup.SetUpActivity
import com.mobigod.cache.preference.IPreferenceManager
import com.mobigod.cache.preference.PreferenceManager
import dagger.android.AndroidInjection
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var preferenceManager: IPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            if (!preferenceManager.hasBeenSetUp){
                SetUpActivity.start(this)
                finish()
            }else {
                if (preferenceManager.userAuthenticated){
                    FlightSchedulesActivity.start(this)
                    finish()
                }else {
                    AuthActivity.start(this)
                    finish()
                }

            }

        }, 2000)

    }
}
