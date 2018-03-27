package com.useradgents.domain.usecase

import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver

interface Interactor<in I, T> {
    fun execute(observer: DisposableObserver<T>, input: I? = null)

    fun execute(input: I? = null): Observable<T>
}