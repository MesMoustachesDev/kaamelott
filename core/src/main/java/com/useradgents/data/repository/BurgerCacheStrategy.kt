package com.useradgents.data.repository

interface BurgerCacheStrategy {
    fun dataIsSet()
    fun isDataValid(): Boolean
}