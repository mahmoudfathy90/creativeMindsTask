package com.creativeapp.application

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.creativeapp.dagger.component.ApplicationComponent
import com.creativeapp.dagger.component.DaggerApplicationComponent
//import com.mazbootapp.dagger.component.DaggerApplicationComponent
import com.tripl3dev.prettystates.StatesConfigFactory

const val BaseUrl= ""
class MyApplication : MultiDexApplication() {

     lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        //set font to all application
        //setFont()
        setUpDaggerComponent()

        StatesConfigFactory.intialize().initDefaultViews()
    }
    // set font by use calligraphy library

   private fun setUpDaggerComponent(){
     applicationComponent = DaggerApplicationComponent.builder().application(this).build()
    }



    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }


}