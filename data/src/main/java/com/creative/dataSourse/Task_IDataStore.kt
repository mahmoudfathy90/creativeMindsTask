package com.creative.dataSourse

import com.creative.Entity.Repo_Entity
import io.reactivex.Observable

interface Task_IDataStore {




    // get all repo
    fun getAllRepo(pageIndex: Int): Observable<List<Repo_Entity>>

}