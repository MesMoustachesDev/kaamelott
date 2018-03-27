package com.useradgents.uaburger.di

import android.app.Application
import com.useradgents.uaburger.MainApplication
import com.useradgents.uaburger.di.module.*
import com.useradgents.uaburger.di.scope.PerApplication
import com.useradgents.uaburger.di.module.SchedulersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Named

@PerApplication
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    NetModule::class,
    ActivityBuilder::class,
    ApiServicesModule::class,
    ServicesModule::class,
    MapperModule::class,
    SchedulersModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun baseUrl(@Named("baseUrl") baseUrl: String): Builder

        fun build(): AppComponent
    }

    fun inject(mainApplication: MainApplication)
}