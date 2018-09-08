package com.creativeapp.dagger.module

import android.app.Application
import android.content.Context
import com.creative.domain.di_interfaces.AppContext
import com.creative.domain.di_interfaces.AppPreferenceName
import com.creative.domain.di_interfaces.AppRemoteUrl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class ApplicationModule {

    @AppRemoteUrl
    @Provides
    fun serviceURl(): String {
        return "http://mazbootapp.com/mazboot_api/"
    }


    @AppPreferenceName
    @Provides
    fun setPreferenceName(): String {
        return "Mazboot"
    }



    @AppContext
    @Provides
    fun context(application: Application): Context {
        return application.applicationContext
    }

}