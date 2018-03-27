package com.useradgents.domain.mapper

import com.useradgents.domain.viewobject.CartItemVO

interface CartItemMapper {
    fun map(burgerId: String): CartItemVO
}