package com.creative.domain.interactor.defaultUsecases

import com.creative.domain.state.BaseVS
import io.reactivex.Observable


abstract class ObservablUseCase<Params>  {
    abstract fun buildUseCase(params: Params): Observable<BaseVS>
}