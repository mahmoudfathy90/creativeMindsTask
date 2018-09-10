package com.creativeapp.ui.base.activity

import android.content.Context
import android.databinding.Observable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.TextView
import com.hannesdorfmann.mosby3.ActivityMviDelegate
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.hannesdorfmann.mosby3.mvi.MviPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.creativeapp.R
import com.creativeapp.application.MyApplication
import com.creativeapp.ui.base.fragment.BaseFragment
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

open abstract class BaseMVIActivity<V,P> : MviActivity<V, P>(), BaseFragment.IActivity where V:MvpView, P:MviPresenter<V,*>{
    override fun createPresenter(): P{
        return BasePresenter() as P
    }
    var delgate : ActivityMVIDelegateImpl<V, P>? = null
    var injectorAll: InjectorAll = InjectorAll()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectorAll.languageModule.isArabic.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                   injectorAll.languageModule.setLocale(this@BaseMVIActivity, injectorAll.languageModule.getLocality()!!)
                recreate()
            }
        })
    }

    open fun setToolbar(toolbarId: Int){
        val toolbar = findViewById<Toolbar>(toolbarId)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_material)
        }
        findViewById<TextView>(R.id.title)?.text = getScreenTitle()
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    open fun getScreenTitle():String = ""

    override fun setScreenTitle(title: String?) {
        findViewById<TextView>(R.id.title)?.text = title
    }

    override fun attachBaseContext(newBase: Context?) {
        injectorAll.attach(newBase!!)

       super.attachBaseContext(CalligraphyContextWrapper.wrap(injectorAll.languageModule.attach(newBase)))
    }

    override fun getMvpDelegate(): ActivityMviDelegate<V, P> {
        if (delgate == null) {
            delgate = ActivityMVIDelegateImpl(this, this)
        }
        return delgate as ActivityMviDelegate<V, P>
    }

    fun application(): MyApplication {
        return this?.applicationContext as MyApplication
    }

    fun replaceFramgment(fragment: Fragment, id:Int){
      //  var theFragment = supportFragmentManager.findFragmentByTag(fragment::class.java.name)
        //if (theFragment == null)
            supportFragmentManager.beginTransaction().replace(id,fragment).commit()
    }
    fun replaceFramgmentWithSave(fragment: Fragment, id:Int){
        var theFragment = supportFragmentManager.findFragmentByTag(fragment::class.java.name)
        if (theFragment == null)
            supportFragmentManager.beginTransaction().replace(id,fragment,fragment::class.java.name)
                    .addToBackStack(fragment::class.java.name)
                    .commit()
    }
}