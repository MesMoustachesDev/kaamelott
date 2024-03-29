package com.useradgents.uaburger.ui.activity.main

import com.useradgents.uaburger.di.scope.PerFragment
import com.useradgents.uaburger.ui.fragment.main.MainFragment
import com.useradgents.uaburger.ui.fragment.main.MainFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentProvider {

    @PerFragment
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun bindMainFragment(): MainFragment
}