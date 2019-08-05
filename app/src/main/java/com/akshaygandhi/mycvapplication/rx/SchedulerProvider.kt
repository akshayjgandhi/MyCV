package com.akshaygandhi.mycvapplication.rx

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun provideSchedulerProvider(): Scheduler
}