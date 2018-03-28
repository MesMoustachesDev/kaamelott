package com.useradgents.uaburger.ui.fragment.main

import com.useradgents.domain.viewobject.SoundVO

interface MainFragmentContract {
    interface View {
        fun showProgress(show: Boolean)
        fun updateSounds(sounds: MutableList<SoundVO>)
        fun showError(message: String)
        fun updateBurgerQuantity(id: String, quantity: Int)
        fun onStart(title: String)
        fun onStop(title: String)
    }

    interface Presenter {
        fun attach()
        fun detach()
        fun refresh()
        fun play(it: String)
    }
}