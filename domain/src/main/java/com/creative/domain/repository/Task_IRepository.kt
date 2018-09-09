package com.creative.domain.repository

import com.creative.domain.model.List_Domain
import com.creative.domain.model.Repo_Domain
import io.reactivex.Observable

interface Task_IRepository {
    fun getMyList(pageIndex:Int):Observable<List<List_Domain>>



    // get all repo
    fun getAllRepo(pageIndex: Int,num:Int): Observable<List<Repo_Domain>>


}