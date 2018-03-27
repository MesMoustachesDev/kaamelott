package io.reactivex.disposables

fun Disposable.addTo(disposable: CompositeDisposable) {
    disposable.add(this)
}