package com.creativeapp.ui.repoTask

import com.creativeapp.ui.base.ActivityList_View
import io.reactivex.Observable

interface Repo_View : ActivityList_View {
    fun getRepoIntentList(): Observable<Pair<Int,Int>>


}