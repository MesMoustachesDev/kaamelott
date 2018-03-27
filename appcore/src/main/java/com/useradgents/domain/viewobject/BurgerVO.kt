package com.useradgents.domain.viewobject

data class BurgerVO(val id: String? = null,
                    val name: String? = null,
                    val description: String? = null,
                    val thumb: String? = null,
                    val price: String? = null,
                    var quantityInCart: Int? = null)