package com.creative.cach.appDatabase

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.creative.cach.dao.Task_Dao
import com.creative.cach.entity.Task_Cache
import javax.inject.Singleton


@Singleton
@Database(entities = arrayOf(
        Task_Cache::class
)
        ,version = 1)
 abstract  class  AppDatabase  :RoomDatabase() {
     abstract  fun  patient_Dao(): Task_Dao



}


