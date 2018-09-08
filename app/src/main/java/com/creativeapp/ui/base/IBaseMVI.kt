package com.creativeapp.ui.base

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.creative.domain.state.BaseVS

interface IBaseMVI : MvpView{
    fun render(baseVS: BaseVS)
}