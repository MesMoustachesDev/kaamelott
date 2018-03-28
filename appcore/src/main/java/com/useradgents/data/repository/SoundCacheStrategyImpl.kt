package com.useradgents.data.repository

import javax.inject.Inject

class SoundCacheStrategyImpl @Inject constructor() : SoundCacheStrategy {

    private var timeStamp: Long? = null

    override fun dataIsSet() {
        timeStamp = System.currentTimeMillis()
    }

    override fun isDataValid(): Boolean =
            timeStamp?.let { System.currentTimeMillis() - it < VALIDITY_TIME_IN_MS } ?: false

    companion object {
        const val VALIDITY_TIME_IN_MS = 30000
    }
}