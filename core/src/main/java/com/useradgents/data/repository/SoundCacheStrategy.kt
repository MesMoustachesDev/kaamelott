package com.useradgents.data.repository

interface SoundCacheStrategy {
    fun dataIsSet()
    fun isDataValid(): Boolean
}