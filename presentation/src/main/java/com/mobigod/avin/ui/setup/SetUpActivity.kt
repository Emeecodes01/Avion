package com.mobigod.avin.ui.setup

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobigod.avin.R
import com.mobigod.avin.base.BaseActivity
import com.mobigod.avin.databinding.ActivitySetupLayoutBinding
import com.mobigod.avin.states.Resource
import com.mobigod.avin.states.State
import com.mobigod.avin.ui.auth.AuthActivity
import com.mobigod.avin.utils.toastWith
import javax.inject.Inject

/**Created by: Emmanuel Ozibo
//on: 05, 2020-02-05
//at: 14:47*/
class SetUpActivity: BaseActivity<ActivitySetupLayoutBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    lateinit var viewModel: SetupViewModel
    lateinit var binding: ActivitySetupLayoutBinding

    override fun layoutRes() = R.layout.activity_setup_layout

    override fun initComponents() {
        binding = getBinding()

        viewModel = ViewModelProvider(this, viewModelFactory)[SetupViewModel::class.java]
        viewModel.updateFirstTime()

        subscribeToLiveData()

    }

    override fun onStart() {
        super.onStart()
        viewModel.startDeviceSetUp()
    }



    private fun subscribeToLiveData() {
        viewModel.percentageLiveData.observe(this, Observer {
            when(it.state){
                State.SUCCESS -> {
                    val currentProgress = it.data!!

                    binding.progressIndicator.progress = currentProgress
                    binding.percentTv.text = "$currentProgress%"
                }
                State.ERROR -> toastWith("An error occurred in saving airport data")
                State.LOADING -> ""
            }
        })


        viewModel.loadingCompleteLiveData.observe(this, Observer {
            when(it.state){
                State.SUCCESS -> {
                    viewModel.setUserSetUp()

                    //check
                    AuthActivity.start(this)
                    finish()
                }
                else -> {
                    //Not really expecting anything
                }
            }
        })
    }


    companion object {
        fun start(context: Context) {
            Intent(context, SetUpActivity::class.java).also {
                context.startActivity(it)
            }
        }
    }
}