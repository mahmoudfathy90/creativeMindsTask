package com.creativeapp.ui.designtask

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.creativeapp.R
import com.creativeapp.databinding.ActivityMyListActivtyBinding
import com.creativeapp.ui.base.activity.BaseActivity

class MyListActivty : BaseActivity() {

    var firstFragment:Boolean = true

    lateinit var binding:ActivityMyListActivtyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_my_list_activty)

        binding.back.setOnClickListener{
            onBackPressed()
        }
        binding.needs.isChecked=true
        setGroupConatiner()
    }

    fun setGroupConatiner(){
       replaceFramgment(FirstContanierFragment(),binding.fragmentContainer.id)
        binding.groupContainer.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.needs -> {
                    binding.needs.setBackgroundResource(R.drawable.radio_selected)
                    binding.others.setBackgroundColor(
                            resources.getColor(android.R.color.transparent))
                    replaceFramgment(FirstContanierFragment(), binding.fragmentContainer.id)
                }
                R.id.others -> {
                    binding.others.setBackgroundResource(R.drawable.radio_selected)
                    binding.needs.setBackgroundColor(
                            resources.getColor(android.R.color.transparent))

                    replaceFramgment(SecondContainerFragment(), binding.fragmentContainer.id)
                }
            }
        }
    }
}
