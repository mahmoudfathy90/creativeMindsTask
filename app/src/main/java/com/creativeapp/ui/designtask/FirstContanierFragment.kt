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

class FirstContanierFragment : Fragment() {


    lateinit var adapter: ViewPagerAdapter
    lateinit var binding: FragmentFirstContanierBinding


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
        val current = arguments?.getInt("radionumber",1)
        adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFrag(MyList_Fragment.newInstance(0, current!!), getString(R.string.wait))
        adapter.addFrag(MyList_Fragment.newInstance(1, current!!), getString(R.string.accepted))
        adapter.addFrag(MyList_Fragment.newInstance(2, current!!), getString(R.string.canceled))
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        return binding.root
    }

}
