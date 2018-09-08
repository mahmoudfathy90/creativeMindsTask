package com.creativeapp.ui.base.activity

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.creative.domain.state.BaseVS
import com.creativeapp.ui.base.MVIBasePresenter

open  abstract class BaseActivity : BaseMVIActivity<MvpView, MVIBasePresenter<MvpView, *>>() {
    override fun createPresenter(): MVIBasePresenter<MvpView, *> {
        return BasePresenter()
    }
}

class BasePresenter:MVIBasePresenter<MvpView, BaseVS>(){
    override fun bindIntents() {

    }


}