package com.mobigod.domain.usecases.base

import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<R, in Params> protected constructor(private val threadExecutor: ThreadExecutor,
                                                      private val postExecutionThread: PostExecutionThread) {

    private val subcriptions = CompositeDisposable()

    abstract fun buildUseCaseObservable(param: Params?): Single<R>

    fun execute(observableObserver: DisposableSingleObserver<R>, params: Params) {
        val observer =  buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
        subcriptions += observer.subscribeWith(observableObserver)
    }

    fun unsubscribe() {
        if (!subcriptions.isDisposed) {
            subcriptions.dispose()
        }
    }

}