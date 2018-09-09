package com.creativeapp.ui.repoTask

import com.creative.domain.interactor.usecases.Repo_UseCase
import com.creative.domain.state.BaseVS
import com.creativeapp.ui.base.MVIBasePresenter


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repo_Presenter @Inject constructor(var usercase: Repo_UseCase) :
        MVIBasePresenter<Repo_View, BaseVS>() {
    override fun bindIntents() {
        var lists = intent(Repo_View::getRepoIntentList)
                .switchMap { usercase.buildUseCase(it.first, it.second) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
        subscribeViewState(lists, Repo_View::render)
    }


}