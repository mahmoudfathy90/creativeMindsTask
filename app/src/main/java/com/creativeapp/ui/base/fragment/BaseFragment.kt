package com.creativeapp.ui.base.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.hannesdorfmann.mosby3.FragmentMviDelegate
import com.hannesdorfmann.mosby3.mvi.MviFragment
import com.hannesdorfmann.mosby3.mvi.MviPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.creativeapp.application.MyApplication
import com.creativeapp.utils.DateAndTimeModel
import java.text.SimpleDateFormat
import java.util.*

abstract class BaseFragment<V,P> : MviFragment<V, P>() where V : MvpView,P: MviPresenter<V, *> {

     var mviDelegate: MVIDelegate<V, P>? = null

    private lateinit var iActivity: IActivity


    override fun onAttach(context: Context?) {
        super.onAttach(context)
    this.iActivity = context as IActivity
    }

    fun application():MyApplication{
        return context?.applicationContext as MyApplication
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    @SuppressLint("SimpleDateFormat")
    fun getDateAndTime() : DateAndTimeModel {
        val calendar = Calendar.getInstance()
        val dateAndTime = SimpleDateFormat("yyyy - MMM  - dd - h:mm a", Locale.ENGLISH)
        val date = SimpleDateFormat("yyyy - MM - dd ", Locale.ENGLISH)
        val time = SimpleDateFormat("h:mm a", Locale.ENGLISH)
        val strDateAndTime = dateAndTime.format(calendar.time)
        val strDate = date.format(calendar.time)
        val strTime = time.format(calendar.time)
        return DateAndTimeModel(strDateAndTime,strDate,strTime)
    }

    override fun getMvpDelegate(): FragmentMviDelegate<V, P> {
        if (mviDelegate == null)
        mviDelegate = MVIDelegate(this, this)
        return mviDelegate!!
    }

    fun setScreenTitle(title:String){
        iActivity.setScreenTitle(title)
    }

    interface IActivity{
        fun setScreenTitle(title:String?)
    }
}

