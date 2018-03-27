package com.useradgents.uaburger.di.module

import com.useradgents.data.network.BurgerAPIService
import com.useradgents.data.network.MockBurgerAPIService
import com.useradgents.di.qualifier.Mocked
import com.useradgents.uaburger.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiServicesModule {

    @Provides
    @PerApplication
    fun provideBurgerApiService(retrofit: Retrofit): BurgerAPIService =
            retrofit.create(BurgerAPIService::class.java)

    @Provides
    @PerApplication
    @Mocked
    fun provideMockBurgerService(service: MockBurgerAPIService): BurgerAPIService = service
}