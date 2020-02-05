package com.mobigod.avin.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.jakewharton.rxbinding3.view.clicks
import com.mobigod.avin.R
import com.mobigod.avin.databinding.MaterialLoadingButtonBinding
import com.mobigod.avin.utils.hide
import com.mobigod.avin.utils.show

/**Created by: Emmanuel Ozibo
//on: 05, 2020-02-05
//at: 00:19*/
class MaterialLoadingButton @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, style: Int = 0):
    FrameLayout(context, attributeSet, style) {

    private var binding: MaterialLoadingButtonBinding

    var setLoadingState: Boolean = false
    set(value) {
        if (value){
            showLoadingState()
        }else{
            showIdleState()
        }
        field = value
    }

    var clickListener: Clicklistener? = null


    init {
        val layoutInflater = LayoutInflater.from(context)
        binding = MaterialLoadingButtonBinding.inflate(layoutInflater, this, true)

        setListeners()
    }


    private fun showLoadingState(){
        binding.btnProgress.show()
        binding.loadingBtn.hide()
    }

    private fun setListeners() {
        binding.loadingBtn.setOnClickListener {
            clickListener?.onClicked()
        }
    }

    private fun showIdleState(){
        binding.btnProgress.hide()
        binding.loadingBtn.show()
    }

    interface Clicklistener {
        fun onClicked()
    }
}