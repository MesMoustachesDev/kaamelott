package com.useradgents.uaburger.ui.fragment.main

import com.useradgents.data.repository.CartTotalQuantityAndPrice
import com.useradgents.domain.mapper.BurgerMapper
import com.useradgents.domain.usecase.ChangeCartItemQuantityUseCase
import com.useradgents.domain.usecase.GetBurgersUseCase
import com.useradgents.domain.usecase.ObserveCartUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.addTo
import io.reactivex.observers.DisposableObserver
import timber.log.Timber
import javax.inject.Inject

class MainFragmentPresenter @Inject constructor(
        private var mAttachedView: MainFragmentContract.View?,
        private val getBurgersUseCase: GetBurgersUseCase,
        private val observeCartUseCase: ObserveCartUseCase,
        private val changeCartItemQuantityUseCase: ChangeCartItemQuantityUseCase,
        private val burgerMapper: BurgerMapper
) : MainFragmentContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun attach() {
        observeCartUseCase.execute(object : DisposableObserver<CartTotalQuantityAndPrice>() {
            override fun onComplete() {
                Timber.e("observeCartUseCase onComplete")
            }

            override fun onNext(t: CartTotalQuantityAndPrice) {
                Timber.e("observeCartUseCase onNext: $t")
                refresh()
            }

            override fun onError(e: Throwable) {
                Timber.e(e, "observeCartUseCase onError: ")
            }
        })

        refresh()
    }

    override fun detach() {
        disposable.dispose()
        getBurgersUseCase.dispose()
        observeCartUseCase.dispose()
        changeCartItemQuantityUseCase.dispose()
        mAttachedView = null
    }

    override fun refresh() {
        getBurgersUseCase.execute()
                .doOnSubscribe { mAttachedView?.showProgress(true) }
                .doAfterTerminate { mAttachedView?.showProgress(false) }
                .subscribe(
                        {
                            Timber.e(" onNext: $it")
                            mAttachedView?.updateBurgers(it.map { burgerMapper.map(it) }.toMutableList())
                        },
                        { Timber.e(it, " onError: ") },
                        { Timber.e(" onComplete") })
                .addTo(disposable)
    }

    override fun changeCartItemQuantity(id: String, deltaQuantity: Int) {
        changeCartItemQuantityUseCase.execute(object : DisposableObserver<Int>() {
            override fun onComplete() {
                Timber.e("changeCartItemQuantityUseCase onComplete")
            }

            override fun onNext(t: Int) {
                Timber.e("changeCartItemQuantityUseCase onNext $t")
                mAttachedView?.updateBurgerQuantity(id, t)
            }

            override fun onError(e: Throwable) {
                Timber.e(e, "changeCartItemQuantityUseCase onError")
            }
        }, ChangeCartItemQuantityUseCase.Param(id, deltaQuantity))
    }
}
