package com.creative.dataStore

import com.creative.Entity.Repo_Entity
import com.creative.dataSourse.Task_ICach
import com.creative.dataSourse.Task_IDataStore


import io.reactivex.Observable
import javax.inject.Inject

class Task_CachDataStore @Inject constructor(private var task_ICach: Task_ICach) : Task_IDataStore {
    override fun getAllRepo(pageIndex: Int): Observable<List<Repo_Entity>> =task_ICach.getAllRepo(pageIndex)

}