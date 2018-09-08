package com.creativeapp.ui.base.fragment

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.creativeapp.R

const  val FRAGMENTNAME = "FRAGMENTNAME"
class BottomSheetDialog_Fragment : BottomSheetDialogFragment(){

    companion object {
        fun open(fragmentManager: FragmentManager,className:String) {
            val fragment = BottomSheetDialog_Fragment()
            val bundle = Bundle()
            bundle.putString(FRAGMENTNAME,className)
            fragment.arguments = bundle
            fragment.show(fragmentManager,className)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val container = FrameLayout(context)
        container.id = R.id.container
        return container
    }



    fun getFragmentClassName():String?{
        return arguments?.getString(FRAGMENTNAME,null)
    }


    fun getFragment(): Class<Fragment>?{
        val clazz: Class<*>?
        try {
            clazz = Class.forName(getFragmentClassName())

            return clazz as Class<Fragment>
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }catch (e:Exception){
            e.printStackTrace()
        }
        return null
    }

    fun openFragment(){
        val fragmentClass = getFragment() ?: return
        val fragment = fragmentClass.newInstance() ?: return
        fragment.arguments = arguments
        if (fragment is IBottomSheet){
            fragment.setBottomSheetDialog(this)
        }
        childFragmentManager.beginTransaction()
                .replace(R.id.container,fragment).commit()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openFragment()
    }
}