package com.bud.app.com.bud.app.core.schedulers

import io.reactivex.Scheduler


interface BaseSchedulerProvider {

    fun io(): Scheduler

    fun ui(): Scheduler
}