package com.mobigod.avin.utils

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 23:24*/

fun Activity.toastWith(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if(value) View.VISIBLE else View.INVISIBLE
    }

fun View.hide() {
    visibility = View.GONE
}


fun View.show() {
    visibility = View.VISIBLE
}


fun View.isShowing() = visibility == View.VISIBLE

fun TextInputEditText.isNotEmpty(): Boolean {
    return this.text!!.isNotEmpty()
}

fun Fragment.showSnackMessage(message: String){
    Snackbar.make(this.view!!, message, Snackbar.LENGTH_LONG)
        .setTextColor(ContextCompat.getColor(this.context!!, android.R.color.white))
        .show()
}
