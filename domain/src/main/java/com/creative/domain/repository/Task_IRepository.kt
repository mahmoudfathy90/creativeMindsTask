package com.creative.domain.repository

import com.creative.domain.model.List_Domain
import io.reactivex.Observable

interface Task_IRepository {
    fun getMyList(pageIndex:Int):Observable<List<List_Domain>>



}