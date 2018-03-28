package com.useradgents.uaburger.ui.activity.main

import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(
        private var mAttachedView: MainActivityContract.View?) : MainActivityContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun attach() {
    }

    override fun detach() {
        disposable.dispose()
        mAttachedView = null
    }
}