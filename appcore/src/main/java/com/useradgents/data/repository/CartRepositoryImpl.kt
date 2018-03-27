package com.useradgents.data.repository

import com.useradgents.data.datasource.BurgerDataSource
import com.useradgents.data.datasource.BurgerSpecification
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
        private val burgerRepository: BurgerDataSource
) : CartRepository {

    /**
     * key : BurgerId
     * value : Quantity
     */
    private val map = sortedMapOf<String, Int>()
    private var totalSize = 0
    private var totalPrice = 0f
    private var observe = BehaviorSubject.create<CartTotalQuantityAndPrice>()

    override fun addToCart(id: String, quantity: Int): Single<Int> {
        val existingQuantity = map[id]
        var newQuantity = existingQuantity?.let { it + quantity } ?: quantity
        if (newQuantity < 0) newQuantity = 0
        map[id] = newQuantity
        totalSize = totalSize - (existingQuantity ?: 0) + newQuantity
        val burger = burgerRepository.query(BurgerSpecification.ByRef(id))[0]
        totalPrice = totalPrice - (existingQuantity?.times(burger.price?.div(100f) ?: 0f)
                ?: 0f) + newQuantity.times(burger.price?.div(100f) ?: 0f)
        return if (newQuantity == 0) {
            removeFromCart(id).andThen(Single.just(0))
        } else {
            observe.onNext(CartTotalQuantityAndPrice(totalSize, totalPrice))
            dump()
            Single.just(newQuantity)
        }
    }

    override fun removeFromCart(id: String): Completable {
        val existingQuantity = map[id]
        if (existingQuantity != null) {
            totalSize -= getNbItemsInCart(id)
            val burger = burgerRepository.query(BurgerSpecification.ByRef(id))[0]
            totalPrice -= getNbItemsInCart(id).times(burger.price?.div(100f) ?: 0f)
            map.remove(id)
        }
        observe.onNext(CartTotalQuantityAndPrice(totalSize, totalPrice))
        dump()
        return Completable.complete()
    }

    override fun clearCart(): Completable {
        map.clear()
        totalSize = 0
        totalPrice = 0f
        observe.onNext(CartTotalQuantityAndPrice(totalSize, totalPrice))
        dump()
        return Completable.complete()
    }

    override fun getNbItemsInCart(id: String): Int = map[id] ?: 0

    override fun getNbItemsInCart(): Int = totalSize

    override fun getTotalPrice(): Float = totalPrice

    override fun getItemIds(): List<String> = map.keys.toList()

    override fun monitor(): Observable<CartTotalQuantityAndPrice> {
        return observe
    }

    private fun dump() {
        Timber.e("DUMP : $map, total=$totalSize")
    }
}
