package com.creativeapp.dagger.component

import android.app.Application
import com.creativeapp.dagger.module.CahceModule
import com.creativeapp.ui.base.activity.InjectorAll
import com.creativeapp.dagger.module.*
import com.creativeapp.ui.designtask.myfragment.MyList_Fragment

import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class,
        PicassoModule::class,
        NetworkModule::class,
        ServiceModule::class,
        DataSourceModule::class,
        RepositryModule::class,
        CahceModule::class
))
interface ApplicationComponent {
    fun inject(injectorAll: InjectorAll)
    fun inject(injectorAll: MyList_Fragment)


    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
        @BindsInstance fun application(application: Application): Builder
    }
}