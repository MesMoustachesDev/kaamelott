package com.useradgents.uaburger.ui.activity.splash

interface SplashActivityContract {
    interface View {
        fun goToMainActivity()
    }

    interface Presenter {
        fun attach()
        fun detach()
    }
}