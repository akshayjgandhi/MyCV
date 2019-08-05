package com.akshaygandhi.mycvapplication.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class IoThreadSchedulerProvider : SchedulerProvider {
    override fun provideSchedulerProvider(): Scheduler {
        return Schedulers.io()
    }
}