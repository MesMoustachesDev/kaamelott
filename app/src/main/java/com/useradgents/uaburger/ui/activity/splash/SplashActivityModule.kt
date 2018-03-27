package com.useradgents.uaburger.ui.activity.splash

import dagger.Binds
import dagger.Module

@Module
abstract class SplashActivityModule {
    @Binds
    abstract fun provideView(view: SplashActivity): SplashActivityContract.View

    @Binds
    abstract fun providePresenter(presenter: SplashActivityPresenter): SplashActivityContract.Presenter
}