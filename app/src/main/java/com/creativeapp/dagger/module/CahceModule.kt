package com.creativeapp.dagger.module

import android.arch.persistence.room.Room
import android.content.Context
import com.creative.domain.di_interfaces.AppContext
import dagger.Module
import javax.inject.Singleton

import com.creative.cach.appDatabase.AppDatabase

import com.creative.cach.dao.Task_Dao
import dagger.Provides


@Singleton
@Module(includes = [ApplicationModule::class])
class CahceModule {

    @Provides
    fun appDatabase(@AppContext context: Context): AppDatabase {
        return Room.databaseBuilder(context,
                AppDatabase::class.java, "mazboot").build()
    }

    @Provides
    fun patientDao(appDatabase: AppDatabase): Task_Dao {
        return appDatabase.patient_Dao()
    }




}