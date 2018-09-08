package com.creative.domain.interactor.usecases

import com.creative.domain.repository.Task_IRepository
import com.creative.domain.state.BaseVS
import io.reactivex.Observable
import javax.inject.Inject

class Task_UseCase @Inject constructor(var task_IRepository: Task_IRepository) {
//    fun buildUseCase(pageIndex: Int): Observable<BaseVS> {
//        return task_IRepository.getAllMonths(pageIndex)
//                .map {
//                    if (it.isEmpty())
//                        BaseVS.Empty()
//                    else
//                        Months_ListResult(it)
//                }
//                .onErrorReturn {
//                    BaseVS.Error(it.message!!)
//                }
//                .startWith(BaseVS.Loading())
//
//
////
//
//    }
}