package com.creative.domain.interactor.defaultUsecases

import io.reactivex.Observable


abstract class ObservableUseCase<ReturnType,Params>  {
    abstract fun buildUseCase(params: Params): Observable<ReturnType>
}