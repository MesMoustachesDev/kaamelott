package com.useradgents.uaburger.ui.fragment.cart

import com.useradgents.domain.mapper.CartItemMapper
import com.useradgents.domain.usecase.ObserveCartItemsAndTotalUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import timber.log.Timber
import javax.inject.Inject

class CartFragmentPresenter @Inject constructor(
        private var mAttachedView: CartFragmentContract.View?,
        private val cartItemMapper: CartItemMapper,
        private val observeCartItemsAndTotalUseCase: ObserveCartItemsAndTotalUseCase
) : CartFragmentContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun attach() {
        observeCartItemsAndTotalUseCase.execute(object : DisposableObserver<ObserveCartItemsAndTotalUseCase.CartItemsAndTotal>() {
            override fun onComplete() {
                Timber.e("observeCart onComplete")
            }

            override fun onNext(t: ObserveCartItemsAndTotalUseCase.CartItemsAndTotal) {
                Timber.e("observeCart onNext: $t")
                mAttachedView?.updateCart(t.burgers.map { cartItemMapper.map(it.ref ?: "0") })
                mAttachedView?.updateTotal("total=${String.format("%.2f", t.totalPrice)} €")
            }

            override fun onError(e: Throwable) {
                Timber.e(e, "observeCart onError: ")
            }
        })
    }

    override fun detach() {
        disposable.dispose()
        observeCartItemsAndTotalUseCase.dispose()
        mAttachedView = null
    }
}