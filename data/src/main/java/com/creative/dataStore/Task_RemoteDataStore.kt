package com.creative.dataStore

import com.creative.Entity.Repo_Entity
import com.creative.dataSourse.Task_IDataStore
import com.creative.dataSourse.Task_IRemote

import io.reactivex.Observable

import javax.inject.Inject
class Task_RemoteDataStore @Inject constructor(private var task_IRemote: Task_IRemote) : Task_IDataStore {
    override fun getAllRepo(pageIndex: Int): Observable<List<Repo_Entity>> =task_IRemote.getAllRepo(pageIndex)

}

