package com.bud.app.di

import com.bud.app.flows.transactions.TransactionsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentsModule::class])
    abstract fun contributeMainActivity(): TransactionsActivity
}