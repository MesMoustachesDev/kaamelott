package com.useradgents.uaburger.di.module

import com.useradgents.di.qualifier.Worker
import com.useradgents.uaburger.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class NetModule {

    @Provides
    @PerApplication
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @PerApplication
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()

    @Provides
    @PerApplication
    fun provideRetrofit(@Named("baseUrl") baseUrl: String, client: OkHttpClient, @Worker scheduler: Scheduler): Retrofit =
            Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(scheduler))
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build()
}