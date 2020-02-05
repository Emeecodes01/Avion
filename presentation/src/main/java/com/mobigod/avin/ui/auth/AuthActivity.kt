package com.mobigod.avin.ui.auth


import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.mobigod.avin.R
import com.mobigod.avin.base.BaseActivity
import com.mobigod.avin.databinding.ActivityAuthLayoutBinding
import com.mobigod.avin.models.auth.TokenModel
import com.mobigod.avin.states.Resource
import javax.inject.Inject
import androidx.lifecycle.Observer
import com.jakewharton.rxbinding3.view.clicks
import com.mobigod.avin.BuildConfig
import com.mobigod.avin.states.State
import com.mobigod.avin.utils.toastWith
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 04:37*/
class AuthActivity: BaseActivity<ActivityAuthLayoutBinding>() {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory



    lateinit var authViewModel: AuthViewModel
    lateinit var binding: ActivityAuthLayoutBinding

    private val subscriptions = CompositeDisposable()


    override fun layoutRes() = R.layout.activity_auth_layout

    override fun initComponents() {
        binding = getBinding()

        binding.clientIdTv.setText(BuildConfig.LUFTHANSA_KEY)
        binding.clientSecretTv.setText(BuildConfig.LUFTHANSA_SECRET)

        authViewModel = ViewModelProvider(this, viewmodelFactory)[AuthViewModel::class.java]

        subscribeToLiveData()

        setUpListeners()
    }


    private fun setUpListeners() {
        subscriptions += binding.signInBtn.clicks().subscribe {
            val clientId = binding.clientIdTv.text.toString()
            val clientSecret = binding.clientSecretTv.text.toString()

            if (validateInput(clientId, clientSecret)) return@subscribe

            authViewModel.loginUser(clientId, clientSecret)
        }
    }




    private fun validateInput(clientId: String, clientSecret: String): Boolean {
        if (clientId.isEmpty()) {
            binding.clientIdTv.error = "Client Id cannot be empty"
            return true
        }

        if (clientSecret.isEmpty()) {
            binding.clientSecretTv.error = "Client Secret cannot be empty"
        }
        return false
    }




    private fun subscribeToLiveData() {
        authViewModel.loginLiveData.observe(this, Observer {
                loginLiveDataHandler(it)
            }
        )
    }



    private fun loginLiveDataHandler(resource: Resource<TokenModel>){
        when(resource.state) {
            State.LOADING -> toastWith("Login Loading")
            State.SUCCESS -> toastWith("Success: ${resource.data?.accessToken}")
            State.ERROR -> toastWith("Error occured: ${resource.message}")
        }
    }

    companion object {
        fun start(context: Context) {
            Intent(context, AuthActivity::class.java).also {
                context.startActivity(it)
            }
        }
    }
}