package com.useradgents.uaburger.ui.fragment.cart


import dagger.Binds
import dagger.Module

@Module
abstract class CartFragmentModule {
    @Binds
    abstract fun provideView(view: CartFragment): CartFragmentContract.View

    @Binds
    abstract fun providePresenter(presenter: CartFragmentPresenter): CartFragmentContract.Presenter

}