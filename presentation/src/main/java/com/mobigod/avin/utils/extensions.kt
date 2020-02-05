package com.mobigod.avin.utils

import android.app.Activity
import android.widget.Toast

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 23:24*/

fun Activity.toastWith(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}