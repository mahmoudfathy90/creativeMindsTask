package com.creativeapp.ui.base.fragment

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.creativeapp.ui.base.activity.BasePresenter

open class SBaseFragment : BaseFragment<MvpView, BasePresenter>() {

    override fun createPresenter(): BasePresenter {
        return BasePresenter()
    }
}