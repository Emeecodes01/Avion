package com.mobigod.avin.ui.auth


import android.content.Context
import android.content.Intent
import com.mobigod.avin.R
import com.mobigod.avin.base.BaseActivity
import com.mobigod.avin.databinding.ActivityAuthLayoutBinding

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 04:37*/
class AuthActivity: BaseActivity<ActivityAuthLayoutBinding>() {
    lateinit var binding: ActivityAuthLayoutBinding

    override fun layoutRes() = R.layout.activity_auth_layout

    override fun initComponents() {
        binding = getBinding()

    }

    companion object {
        fun start(context: Context) {
            Intent(context, AuthActivity::class.java).also {
                context.startActivity(it)
            }
        }
    }
}