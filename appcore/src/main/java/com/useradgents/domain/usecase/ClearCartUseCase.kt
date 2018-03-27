package com.useradgents.domain.usecase

import com.useradgents.data.repository.CartRepository
import com.useradgents.di.qualifier.UI
import com.useradgents.di.qualifier.Worker
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class ClearCartUseCase @Inject constructor(
        private val cartRepository: CartRepository,
        @Worker worker: Scheduler,
        @UI ui: Scheduler
) : UseCase<Void, Void>(worker, ui) {

    override fun createObservable(input: Void?): Observable<Void> = cartRepository.clearCart().toObservable()

}