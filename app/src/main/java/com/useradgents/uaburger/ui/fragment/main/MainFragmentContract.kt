package com.useradgents.uaburger.ui.fragment.main

import com.useradgents.domain.viewobject.BurgerVO

interface MainFragmentContract {
    interface View {
        fun showProgress(show: Boolean)
        fun updateBurgers(burgers: MutableList<BurgerVO>)
        fun showError(message: String)
        fun updateBurgerQuantity(id: String, quantity: Int)
    }

    interface Presenter {
        fun attach()
        fun detach()
        fun refresh()
        fun changeCartItemQuantity(id: String, deltaQuantity: Int)
    }
}