package com.creativeapp.dagger.module

import com.creative.dataSource.Task_CahcImp
import com.creative.dataSource.Task_RemoteImp
import com.creative.dataSourse.Task_ICach
import com.creative.dataSourse.Task_IRemote
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule{

    @Provides
    fun provideTaskICach(task: Task_CahcImp):Task_ICach{
        return  task
    }

    @Provides
    fun provideTaskIremote(remote: Task_RemoteImp):Task_IRemote{
        return  remote
    }








}