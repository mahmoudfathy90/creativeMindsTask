package com.creativeapp.ui.designtask

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.creativeapp.R
import com.creativeapp.databinding.ActivityMyListActivtyBinding
import com.creativeapp.ui.base.activity.BaseActivity

class MyListActivty : BaseActivity() {

    lateinit var binding:ActivityMyListActivtyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_my_list_activty)

        replaceFramgment(FirstContanierFragment(),binding.fragmentContainer.id)

    }
}
