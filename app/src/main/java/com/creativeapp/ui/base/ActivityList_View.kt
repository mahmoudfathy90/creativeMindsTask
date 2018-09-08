package com.creativeapp.ui.base

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.creative.domain.state.BaseVS
import io.reactivex.Observable

interface ActivityList_View : MvpView{
    fun getIntentList(): Observable<Int>
    fun render(baseVS: BaseVS)
}