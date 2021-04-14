package com.hechikasoft.jaaculator.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Provides
//    @Singleton
//    fun provideAuthInterceptorOkHttpClient(
//    ): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(authInterceptor)
//            .build()
//    }

}