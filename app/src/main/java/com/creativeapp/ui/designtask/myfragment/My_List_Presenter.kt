package com.creativeapp.ui.designtask.myfragment

import com.creative.domain.interactor.usecases.MyList_UseCase
import com.creative.domain.state.BaseVS
import com.creativeapp.ui.base.MVIBasePresenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class My_List_Presenter @Inject constructor(var usercase: MyList_UseCase):
        MVIBasePresenter<MyList_View, BaseVS>() {
    override fun bindIntents() {
        var lists = intent(MyList_View::getIntentList)
                .switchMap(usercase::buildUseCase)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
        subscribeViewState(lists, MyList_View::render)
    }


}