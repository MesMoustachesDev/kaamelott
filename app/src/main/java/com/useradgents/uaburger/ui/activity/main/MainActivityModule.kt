package com.useradgents.uaburger.ui.activity.main

import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {
    @Binds
    abstract fun provideView(view: MainActivity): MainActivityContract.View

    @Binds
    abstract fun providePresenter(presenter: MainActivityPresenter): MainActivityContract.Presenter
}