package com.useradgents.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CartRepository {
    fun addToCart(id: String, quantity: Int): Single<Int>
    fun removeFromCart(id: String): Completable
    fun clearCart(): Completable
    fun getNbItemsInCart(id: String): Int
    fun getNbItemsInCart(): Int
    fun getTotalPrice(): Float
    fun getItemIds(): List<String>
    fun monitor(): Observable<CartTotalQuantityAndPrice>
}

data class CartTotalQuantityAndPrice(val totalQuantity: Int, val totalPrice: Float)