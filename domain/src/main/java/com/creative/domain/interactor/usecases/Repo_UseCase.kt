package com.creative.domain.interactor.usecases

import com.creative.domain.repository.Task_IRepository
import com.creative.domain.state.BaseVS
import com.creative.domain.state.List_result
import com.creative.domain.state.Repo_result
import io.reactivex.Observable
import javax.inject.Inject

class Repo_UseCase @Inject constructor(var task_IRepository: Task_IRepository) {
    fun buildUseCase(pageIndex: Int): Observable<BaseVS> {
        return task_IRepository.getAllRepo(pageIndex)
                .map {
                    if (it.isEmpty())
                        BaseVS.Empty()
                    else
                        Repo_result(it)
                }
                .onErrorReturn {
                    BaseVS.Error(it.message!!)
                }
                .startWith(BaseVS.Loading())

    }
}