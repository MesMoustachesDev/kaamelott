package com.useradgents.domain.mapper

import com.useradgents.domain.model.Burger
import com.useradgents.domain.viewobject.BurgerVO

interface BurgerMapper {
    fun map(input: Burger): BurgerVO
}