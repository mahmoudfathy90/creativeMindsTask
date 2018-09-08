package com.creativeapp.ui.designtask


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.creativeapp.ui.designtask.myfragment.MyList_Fragment
import com.creativeapp.utils.ViewPagerAdapter
import android.support.v4.app.FragmentManager
import com.creativeapp.R
import com.creativeapp.databinding.FragmentFirstContanierBinding
import com.creativeapp.ui.base.fragment.SBaseFragment

class FirstContanierFragment : SBaseFragment() {


    lateinit var adapter: ViewPagerAdapter
    lateinit var binding: FragmentFirstContanierBinding
    var tabnum: Int = 1
    var radionum: Int = 1

    companion object {
        fun newInstance(radioNum: Int): Fragment {
            var frag: FirstContanierFragment = FirstContanierFragment()
            val args = Bundle()
            args.putInt("radionumber", radioNum)
            frag.arguments = args
            return frag

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first_contanier, container, false)
        var fragmentManager: FragmentManager = activity!!.supportFragmentManager
        adapter = ViewPagerAdapter(fragmentManager)

        var bundle = arguments
        radionum = bundle!!.getInt("radionumber", 1)
        adapter.addFrag(MyList_Fragment.newInstance(0, radionum), getString(R.string.wait))
        adapter.addFrag(MyList_Fragment.newInstance(1, radionum), getString(R.string.accepted))
        adapter.addFrag(MyList_Fragment.newInstance(2, radionum), getString(R.string.canceled))
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        return binding.root
    }

}
