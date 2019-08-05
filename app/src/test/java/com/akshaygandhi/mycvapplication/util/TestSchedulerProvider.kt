package com.akshaygandhi.mycvapplication.util

import com.akshaygandhi.mycvapplication.rx.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider : SchedulerProvider {
    override fun provideSchedulerProvider(): Scheduler {
        return Schedulers.trampoline()
    }
}