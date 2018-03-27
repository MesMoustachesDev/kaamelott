package com.useradgents.domain.usecase

import com.useradgents.domain.model.Burger
import com.useradgents.data.repository.BurgerRepository
import com.useradgents.data.repository.CartRepository
import com.useradgents.di.qualifier.UI
import com.useradgents.di.qualifier.Worker
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class ObserveCartItemsAndTotalUseCase @Inject constructor(
        private val burgerRepository: BurgerRepository,
        private val cartRepository: CartRepository,
        @Worker worker: Scheduler,
        @UI ui: Scheduler
) : UseCase<Void, ObserveCartItemsAndTotalUseCase.CartItemsAndTotal>(worker, ui) {

    override fun createObservable(input: Void?): Observable<CartItemsAndTotal> {
        return cartRepository
                .monitor()
                .flatMap { update ->
                    burgerRepository.getBurgersWithIds(cartRepository.getItemIds())
                            .toList()
                            .toObservable()
                            .map { CartItemsAndTotal(it, update.totalPrice) }
                }
    }

    data class CartItemsAndTotal(val burgers: List<Burger>,
                                 val totalPrice: Float)
}