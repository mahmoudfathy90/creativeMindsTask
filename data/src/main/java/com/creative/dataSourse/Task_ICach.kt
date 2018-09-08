package com.creative.dataSourse

import io.reactivex.Observable

interface Task_ICach {
    fun isCached(): Boolean
    fun isExpired(): Boolean
    // add drug



    // get drugs
   // fun getDrugsType(pageIndex: Int): Observable<List<Drugs_TypeEnity>>



}