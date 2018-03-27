package com.useradgents.uaburger.ui.fragment.main

import dagger.Binds
import dagger.Module

@Module
abstract class MainFragmentModule {
    @Binds
    abstract fun provideView(view: MainFragment): MainFragmentContract.View

    @Binds
    abstract fun providePresenter(presenter: MainFragmentPresenter): MainFragmentContract.Presenter
}