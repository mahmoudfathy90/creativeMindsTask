package com.creative.dataStore

import com.creative.dataSourse.Task_ICach
import com.creative.dataSourse.Task_IDataStore


import io.reactivex.Observable
import javax.inject.Inject

class Task_CachDataStore @Inject constructor(private var task_ICach: Task_ICach) : Task_IDataStore {

}