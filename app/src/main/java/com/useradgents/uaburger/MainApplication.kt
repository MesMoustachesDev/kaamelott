package com.useradgents.uaburger

import android.app.Activity
import android.app.Application
import com.useradgents.uaburger.di.initAppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject


class MainApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        initAppInjector(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            //Do something
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}