package com.useradgents.uaburger.di.module

import android.app.Application
import android.content.Context
import com.useradgents.uaburger.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @PerApplication
    fun provideContext(app: Application): Context = app
}