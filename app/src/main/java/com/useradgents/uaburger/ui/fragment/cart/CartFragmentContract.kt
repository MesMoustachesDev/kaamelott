package com.useradgents.uaburger.ui.fragment.cart

import com.useradgents.domain.viewobject.CartItemVO

interface CartFragmentContract {
    interface View {
        fun updateCart(cartItems: List<CartItemVO>)
        fun updateTotal(formatTotal: String)
    }

    interface Presenter {
        fun attach()
        fun detach()
    }
}