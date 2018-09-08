package com.creative.dataSourse

import com.creative.Entity.Repo_Entity
import io.reactivex.Observable

interface Task_ICach {
    fun isCached(): Boolean
    fun isExpired(): Boolean
    // add drug



    // get all repo
    fun getAllRepo(pageIndex: Int): Observable<List<Repo_Entity>>



}