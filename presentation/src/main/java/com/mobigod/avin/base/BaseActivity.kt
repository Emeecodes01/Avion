package com.mobigod.avin.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity

/**Created by: Emmanuel Ozibo
//on: 03, 2020-02-03
//at: 04:27*/
abstract class BaseActivity<T: ViewDataBinding>: DaggerAppCompatActivity() {
    private lateinit var binding: T

    open var TAG: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes())
        initComponents()
    }

    @LayoutRes
    abstract fun layoutRes(): Int

    abstract fun initComponents()

    fun getBinding() = binding
}