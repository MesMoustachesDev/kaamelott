package com.useradgents.domain.usecase

import com.useradgents.domain.model.Burger
import com.useradgents.data.repository.BurgerRepository
import com.useradgents.di.qualifier.UI
import com.useradgents.di.qualifier.Worker
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetBurgersUseCase @Inject constructor(
        private val burgerRepository: BurgerRepository,
        @Worker worker: Scheduler,
        @UI ui: Scheduler
) : UseCase<Void, List<Burger>>(worker, ui) {

    override fun createObservable(input: Void?): Observable<List<Burger>> = burgerRepository.getBurgers().toList().toObservable()
}