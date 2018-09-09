package com.creativeapp.dagger.module


import com.creative.domain.repository.Task_IRepository

import com.creative.repository.Task_Repository
import dagger.Module
import dagger.Provides


@Module
class RepositryModule {


    // provide  Task_IRepository
    @Provides
    fun provideTaskRepositry(task_Repository: Task_Repository): Task_IRepository {
        return task_Repository
    }














}