package com.useradgents.uaburger.ui.activity.main

import com.useradgents.data.repository.CartTotalQuantityAndPrice
import com.useradgents.domain.usecase.ClearCartUseCase
import com.useradgents.domain.usecase.ObserveCartUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import timber.log.Timber
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(
        private var mAttachedView: MainActivityContract.View?,
        private val observeCartUseCase: ObserveCartUseCase,
        private val clearCartUseCase: ClearCartUseCase
) : MainActivityContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun attach() {
        observeCartUseCase.execute(object : DisposableObserver<CartTotalQuantityAndPrice>() {
            override fun onComplete() {
                Timber.e("observerCart onComplete")
            }

            override fun onNext(t: CartTotalQuantityAndPrice) {
                Timber.e("observerCart onNext: $t")
                mAttachedView?.updateCartView(MainActivityContract.CartCountPrice(
                        t.totalQuantity.toString(),
                        String.format("%.02f €", t.totalPrice),
                        t.totalQuantity != 0))
            }

            override fun onError(e: Throwable) {
                Timber.e(e, "observerCart onError: ")
            }
        })
    }

    override fun detach() {
        disposable.dispose()
        observeCartUseCase.dispose()
        clearCartUseCase.dispose()
        mAttachedView = null
    }

    override fun clearCart() {
        clearCartUseCase.execute(object : DisposableObserver<Void>() {
            override fun onComplete() {
                Timber.e("clearCartUseCase onComplete")
            }

            override fun onNext(t: Void) {
                Timber.e("clearCartUseCase onNext: $t")
            }

            override fun onError(e: Throwable) {
                Timber.e(e, "clearCartUseCase onError: ")
            }
        })
    }
}