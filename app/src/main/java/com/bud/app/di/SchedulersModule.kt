package com.bud.app.di


import com.bud.app.di.scopes.PerApplication
import com.bud.app.com.bud.app.core.schedulers.BaseSchedulerProvider
import com.bud.app.com.bud.app.core.schedulers.SchedulerProvider

import dagger.Module
import dagger.Provides

@Module
class SchedulersModule {
    @Provides
    @PerApplication
    fun providesSchedulerProvider(): BaseSchedulerProvider = SchedulerProvider()
}