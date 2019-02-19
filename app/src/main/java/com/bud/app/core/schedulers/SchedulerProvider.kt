package com.bud.app.com.bud.app.core.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider : BaseSchedulerProvider {

    override fun io(): Scheduler =
        Schedulers.io()

    override fun ui(): Scheduler =
        AndroidSchedulers.mainThread()

}