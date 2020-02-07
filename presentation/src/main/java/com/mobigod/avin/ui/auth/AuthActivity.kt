package com.mobigod.avin.ui.auth


import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.mobigod.avin.R
import com.mobigod.avin.base.BaseActivity
import com.mobigod.avin.databinding.ActivityAuthLayoutBinding
import com.mobigod.avin.models.auth.TokenModel
import com.mobigod.avin.states.Resource
import javax.inject.Inject
import androidx.lifecycle.Observer
import com.mobigod.avin.BuildConfig
import com.mobigod.avin.states.State
import com.mobigod.avin.ui.flights.FlightSchedulesActivity
import com.mobigod.avin.ui.widget.MaterialLoadingButton
import com.mobigod.avin.utils.toastWith
import io.reactivex.disposables.CompositeDisposable

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 04:37*/
class AuthActivity: BaseActivity<ActivityAuthLayoutBinding>() {
    override var TAG: String = AuthActivity::class.java.simpleName

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
        binding.materialLoadingBtn.clickListener = object : MaterialLoadingButton.Clicklistener {
            override fun onClicked() {
                val clientId = binding.clientIdTv.text.toString()
                val clientSecret = binding.clientSecretTv.text.toString()

                Log.d(TAG, "ON CLICKED CALLED")

                if (validateInput(clientId, clientSecret)) return

                authViewModel.loginUser(clientId, clientSecret)
            }

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
            State.LOADING -> {
                binding.materialLoadingBtn.setLoadingState = true
            }
            State.SUCCESS -> {
                binding.materialLoadingBtn.setLoadingState = false
                //toastWith("Success: ${resource.data?.accessToken}")
                FlightSchedulesActivity.start(this)
            }
            State.ERROR -> {
                binding.materialLoadingBtn.setLoadingState = false
                toastWith("Error occured: ${resource.message}")
            }
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