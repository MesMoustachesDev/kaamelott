package com.useradgents.domain.usecase

import com.useradgents.data.repository.CartRepository
import com.useradgents.di.qualifier.UI
import com.useradgents.di.qualifier.Worker
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class ChangeCartItemQuantityUseCase @Inject constructor(
        private val cartRepository: CartRepository,
        @Worker worker: Scheduler,
        @UI ui: Scheduler
) : UseCase<ChangeCartItemQuantityUseCase.Param, Int>(worker, ui) {

    override fun createObservable(input: Param?): Observable<Int> {
        input?.let { return cartRepository.addToCart(input.id, input.deltaQuantity).toObservable() }
                ?: return Observable.error(IllegalArgumentException("params cannot be null"))
    }

    data class Param(val id: String,
                     val deltaQuantity: Int)

}