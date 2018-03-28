package com.useradgents.uaburger.di.module

import com.useradgents.data.datasource.MemorySoundDataSource
import com.useradgents.data.datasource.SoundDataSource
import com.useradgents.data.repository.SoundCacheStrategy
import com.useradgents.data.repository.SoundCacheStrategyImpl
import com.useradgents.data.repository.SoundRepository
import com.useradgents.data.repository.SoundRepositoryImpl
import com.useradgents.uaburger.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class ServicesModule {

    @Provides
    @PerApplication
    fun provideSoundRepository(repo: SoundRepositoryImpl): SoundRepository = repo

    @Provides
    @PerApplication
    fun provideSoundDataSource(ds: MemorySoundDataSource): SoundDataSource = ds

    @Provides
    @PerApplication
    fun provideSoundCacheStrategy(cacheStrategy: SoundCacheStrategyImpl): SoundCacheStrategy = cacheStrategy
}