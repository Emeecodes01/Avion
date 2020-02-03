package com.mobigod.avin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mobigod.avin.features.auth.AuthActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            AuthActivity.start(this)
        }, 2000)

    }
}
