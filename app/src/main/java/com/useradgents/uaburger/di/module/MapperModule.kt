package com.useradgents.uaburger.di.module

import com.useradgents.domain.mapper.SoundMapper
import com.useradgents.domain.mapper.SoundMapperImpl
import com.useradgents.uaburger.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    @PerApplication
    fun provideSoundMapper(mapper: SoundMapperImpl): SoundMapper = mapper
}