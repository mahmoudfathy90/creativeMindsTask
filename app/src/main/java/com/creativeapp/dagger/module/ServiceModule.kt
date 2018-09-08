package com.creativeapp.dagger.module

import com.creative.service.Task_IService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class ServiceModule {
    @Provides
    fun providePatientService(retrofit: Retrofit):Task_IService{
       return retrofit.create(Task_IService::class.java)
    }



}