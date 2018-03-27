package com.useradgents.domain.mapper

import com.useradgents.domain.model.Burger
import com.useradgents.data.repository.CartRepository
import com.useradgents.domain.viewobject.BurgerVO
import javax.inject.Inject

class BurgerMapperImpl @Inject constructor(
        private val cartRepository: CartRepository
) : BurgerMapper {

    override fun map(input: Burger): BurgerVO {
        return BurgerVO(
                input.ref,
                input.title,
                input.description,
                input.thumbnail,
                String.format("%.02f €", input.price?.div(100f)),
                input.ref?.let { ref -> cartRepository.getNbItemsInCart(ref) })
    }
}