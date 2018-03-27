package com.useradgents.data.network

import com.useradgents.domain.model.Burger
import io.reactivex.Single
import javax.inject.Inject

class MockBurgerAPIService @Inject constructor() : BurgerAPIService {
    override fun getBurgers(): Single<List<Burger>> = Single.just(listOf(Burger(title = "test title")))
}