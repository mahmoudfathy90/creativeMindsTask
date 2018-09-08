package com.creative.domain.interactor.defaultUsecases

import io.reactivex.Observable

abstract class SingleUseCase<ReturnType,Params>  {
    abstract fun buildUseCase(params: Params): Observable<ReturnType>
}
