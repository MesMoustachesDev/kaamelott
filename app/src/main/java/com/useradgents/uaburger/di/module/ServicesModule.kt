package com.useradgents.uaburger.di.module

import com.useradgents.data.datasource.BurgerDataSource
import com.useradgents.data.repository.BurgerCacheStrategy
import com.useradgents.data.repository.BurgerRepository
import com.useradgents.data.repository.CartRepository
import com.useradgents.data.datasource.MemoryBurgersDataSource
import com.useradgents.data.repository.BurgerCacheStrategyImpl
import com.useradgents.data.repository.BurgerRepositoryImpl
import com.useradgents.data.repository.CartRepositoryImpl
import com.useradgents.uaburger.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class ServicesModule {

    @Provides
    @PerApplication
    fun provideBurgerRepository(repo: BurgerRepositoryImpl): BurgerRepository = repo

    @Provides
    @PerApplication
    fun provideBurgerDataSource(ds: MemoryBurgersDataSource): BurgerDataSource = ds

    @Provides
    @PerApplication
    fun provideBurgerCacheStrategy(cacheStrategy: BurgerCacheStrategyImpl): BurgerCacheStrategy = cacheStrategy

    @Provides
    @PerApplication
    fun provideCartRepository(repo: CartRepositoryImpl): CartRepository = repo
}