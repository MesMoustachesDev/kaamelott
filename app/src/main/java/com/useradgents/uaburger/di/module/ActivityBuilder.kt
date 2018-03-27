package com.useradgents.uaburger.di.module

import com.useradgents.uaburger.di.scope.PerActivity
import com.useradgents.uaburger.ui.activity.main.MainActivity
import com.useradgents.uaburger.ui.activity.main.MainActivityFragmentProvider
import com.useradgents.uaburger.ui.activity.main.MainActivityModule
import com.useradgents.uaburger.ui.activity.splash.SplashActivity
import com.useradgents.uaburger.ui.activity.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = [
        MainActivityModule::class,
        MainActivityFragmentProvider::class
    ])
    abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [
        SplashActivityModule::class
    ])
    abstract fun bindSplashActivity(): SplashActivity
}