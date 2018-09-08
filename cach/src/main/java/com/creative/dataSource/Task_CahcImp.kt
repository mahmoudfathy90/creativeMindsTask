package com.creative.dataSource

import com.creative.Entity.Repo_Entity
import com.creative.dataSourse.Task_ICach
import com.creative.cach.dao.Task_Dao
import io.reactivex.Observable

import javax.inject.Inject



class Task_CahcImp   @Inject constructor(val task:Task_Dao) : Task_ICach {
    override fun getAllRepo(pageIndex: Int): Observable<List<Repo_Entity>> {
        return throw Exception()
    }

    override fun isCached(): Boolean = false

    override fun isExpired(): Boolean = false


}