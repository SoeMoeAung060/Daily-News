package com.soe.dailynews.service.module

import com.soe.dailynews.service.AccountService
import com.soe.dailynews.service.impl.AccountServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Binds
    abstract fun provideAccountService(
        impl: AccountServiceImpl): AccountService

}