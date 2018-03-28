package com.useradgents.uaburger.ui.activity.main

interface MainActivityContract {
    interface View {
    }

    interface Presenter {
        fun attach()
        fun detach()
    }
}