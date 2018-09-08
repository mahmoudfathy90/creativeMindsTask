package com.creativeapp.ui.base.activity

import android.content.Context
import com.creative.module.LanguageModule
import com.creativeapp.application.MyApplication
import javax.inject.Inject

class InjectorAll{
    @Inject
    lateinit var languageModule: LanguageModule

    fun attach(context: Context){
        (context.applicationContext as MyApplication).applicationComponent.inject(this)
    }
}
