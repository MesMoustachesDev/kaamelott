package com.useradgents.uaburger.di.module

import com.useradgents.data.network.MockSoundAPIService
import com.useradgents.data.network.SoundAPIService
import com.useradgents.di.qualifier.Mocked
import com.useradgents.uaburger.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiServicesModule {

    @Provides
    @PerApplication
    fun provideSoundApiService(retrofit: Retrofit): SoundAPIService =
            retrofit.create(SoundAPIService::class.java)

    @Provides
    @PerApplication
    @Mocked
    fun provideMockSoundService(service: MockSoundAPIService): SoundAPIService = service
}