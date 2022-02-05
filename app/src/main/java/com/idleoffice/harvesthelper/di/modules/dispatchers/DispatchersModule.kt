package com.idleoffice.harvesthelper.di.modules.dispatchers

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatchersModule {

    @Binds
    abstract fun bindDispatchers(dispatcherProvider: DispatcherProviderImpl): DispatcherProvider

}