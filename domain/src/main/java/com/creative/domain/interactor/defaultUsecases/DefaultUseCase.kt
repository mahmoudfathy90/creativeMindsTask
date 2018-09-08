package com.creative.domain.interactor.defaultUsecases

abstract class DefaultUseCase<ReturnType,Params>{
    abstract fun buildUseCase(params: Params):ReturnType
}