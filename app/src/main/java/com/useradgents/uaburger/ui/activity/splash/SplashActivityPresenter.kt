package com.useradgents.uaburger.ui.activity.splash

import javax.inject.Inject

class SplashActivityPresenter @Inject constructor(
        private var mAttachedView: SplashActivityContract.View?
) : SplashActivityContract.Presenter {

    override fun attach() {
    }

    override fun detach() {
        mAttachedView = null
    }
}