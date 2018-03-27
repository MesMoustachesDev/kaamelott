package com.useradgents.data.repository

import com.useradgents.domain.model.Burger
import io.reactivex.Observable

interface BurgerRepository {
    fun getBurgers(): Observable<Burger>
    fun getBurgersWithIds(burgers: List<String>): Observable<Burger>
}