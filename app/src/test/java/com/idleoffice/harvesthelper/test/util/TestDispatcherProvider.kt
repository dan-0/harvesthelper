package com.idleoffice.harvesthelper.test.util

import com.idleoffice.harvesthelper.di.modules.dispatchers.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler

class TestDispatcherProvider(testDispatcher: TestCoroutineScheduler): DispatcherProvider {

    private val dispatcher = StandardTestDispatcher(testDispatcher)

    override val io: CoroutineDispatcher = dispatcher
    override val default: CoroutineDispatcher = dispatcher
    override val main: CoroutineDispatcher = dispatcher
}