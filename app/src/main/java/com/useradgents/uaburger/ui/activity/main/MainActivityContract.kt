package com.useradgents.uaburger.ui.activity.main

interface MainActivityContract {
    interface View {
        fun updateCartView(updateCart: CartCountPrice)
    }

    interface Presenter {
        fun attach()
        fun detach()
        fun clearCart()
    }

    data class CartCountPrice(val totalQuantity: String, val totalPrice: String, val shouldBeDisplayed: Boolean)
}