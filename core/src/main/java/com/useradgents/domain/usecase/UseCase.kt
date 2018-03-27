package com.useradgents.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

abstract class UseCase<in REQUEST, RESPONSE> constructor(
        private val worker: Scheduler,
        private val ui: Scheduler
) : Interactor<REQUEST, RESPONSE> {

    private val disposable = CompositeDisposable()

    abstract fun createObservable(input: REQUEST?): Observable<RESPONSE>

    override fun execute(observer: DisposableObserver<RESPONSE>, input: REQUEST?) {
        disposable.add(createObservable(input)
                .subscribeOn(worker)
                .observeOn(ui)
                .subscribeWith(observer))
    }

    override fun execute(input: REQUEST?): Observable<RESPONSE> = createObservable(input).subscribeOn(worker).observeOn(ui)

    fun dispose() {
        disposable.dispose()
    }
}