package com.useradgents.uaburger.di.module

import com.useradgents.domain.mapper.BurgerMapper
import com.useradgents.domain.mapper.BurgerMapperImpl
import com.useradgents.domain.mapper.CartItemMapper
import com.useradgents.domain.mapper.CartItemMapperImpl
import com.useradgents.uaburger.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    @PerApplication
    fun provideBurgerMapper(mapper: BurgerMapperImpl): BurgerMapper = mapper

    @Provides
    @PerApplication
    fun provideCartItemMapper(mapper: CartItemMapperImpl): CartItemMapper = mapper
}