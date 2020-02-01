package com.mobigod.domain.usecases.base

import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in Params> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread) {

    private val subcriptions = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(param: Params?): Completable



    fun execute(params: Params): Completable {
        return buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
    }


    fun unsubscribe() {
        if (!subcriptions.isDisposed) {
            subcriptions.dispose()
        }
    }

}