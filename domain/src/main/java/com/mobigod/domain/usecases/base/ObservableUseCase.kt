package com.mobigod.domain.usecases.base

import com.mobigod.domain.executors.PostExecutionThread
import com.mobigod.domain.executors.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<R, in Params> constructor(private val threadExecutor: ThreadExecutor,
                                                      private val postExecutionThread: PostExecutionThread) {

    private val subcriptions = CompositeDisposable()
    protected abstract fun buildUseCaseObservable(param: Params): Observable<R>


    fun execute(observableObserver: DisposableObserver<R>, params: Params) {
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