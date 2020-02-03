package com.mobigod.avin.features.auth


import android.content.Context
import android.content.Intent
import com.mobigod.avin.R
import com.mobigod.avin.base.BaseActivity
import com.mobigod.avin.databinding.ActivityAuthLayoutBinding
import javax.inject.Inject
import javax.inject.Named

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 04:37*/
class AuthActivity: BaseActivity<ActivityAuthLayoutBinding>() {
    lateinit var binding: ActivityAuthLayoutBinding

    @Inject
    @Named("base_url")
    lateinit var appSecret: String

    override fun layoutRes() = R.layout.activity_auth_layout

    override fun initComponents() {
        binding = getBinding()
        binding.textView.text = appSecret

    }

    companion object {
        fun start(context: Context) {
            Intent(context, AuthActivity::class.java).also {
                context.startActivity(it)
            }
        }
    }
}