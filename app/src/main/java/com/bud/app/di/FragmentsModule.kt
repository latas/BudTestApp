package com.bud.app.di

import com.bud.app.flows.transactions.TransactionsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {
    @ContributesAndroidInjector
    abstract fun contributesTransactionsFragment(): TransactionsFragment
}