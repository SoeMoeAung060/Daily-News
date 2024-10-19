package com.soe.dailynews.di.authenticationModule.module

import com.soe.dailynews.di.authenticationModule.AccountService
import com.soe.dailynews.di.authenticationModule.impl.AccountServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Binds
    abstract fun provideAccountService(
        impl: AccountServiceImpl
    ): AccountService

}