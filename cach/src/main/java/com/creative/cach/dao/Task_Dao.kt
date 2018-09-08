package com.creative.cach.dao

import android.arch.persistence.room.*
import com.creative.cach.entity.Task_Cache
import io.reactivex.Flowable


@Dao
interface Task_Dao {
//
    // get all drugs

    // get all drugs type
    @Query("Select * From Task_Cache")
    fun getAllTask(): Flowable<List<Task_Cache>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTaskList(drugs: Array<Task_Cache>)




}





