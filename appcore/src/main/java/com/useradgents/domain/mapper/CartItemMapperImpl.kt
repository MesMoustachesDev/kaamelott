package com.useradgents.domain.mapper

import com.useradgents.data.datasource.BurgerDataSource
import com.useradgents.data.repository.CartRepository
import com.useradgents.data.datasource.BurgerSpecification
import com.useradgents.domain.viewobject.CartItemVO
import javax.inject.Inject

class CartItemMapperImpl @Inject constructor(
        private val burgerRepository: BurgerDataSource,
        private val cartRepository: CartRepository
) : CartItemMapper {

    override fun map(burgerId: String): CartItemVO {
        val burger = burgerRepository.query(BurgerSpecification.ByRef(burgerId))[0]
        val nbBurgers = cartRepository.getNbItemsInCart(burgerId)

        return CartItemVO(
                title = burger.title ?: "???",
                quantity = "x$nbBurgers",
                totalPrice = String.format("%.2f€", burger.price?.times(nbBurgers)?.div(100)))
    }
}