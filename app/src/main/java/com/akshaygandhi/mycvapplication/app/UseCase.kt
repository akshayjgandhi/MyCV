package com.akshaygandhi.mycvapplication.app

import com.akshaygandhi.mycvapplication.rx.IoThreadSchedulerProvider
import com.akshaygandhi.mycvapplication.rx.MainThreadSchedulerProvider
import com.akshaygandhi.mycvapplication.rx.SchedulerProvider
import io.reactivex.Flowable

abstract class UseCase<REQUEST, RESPONSE> @JvmOverloads constructor(
    private val subscribeOnScheduler: SchedulerProvider = IoThreadSchedulerProvider(),
    private val observeOnScheduler: SchedulerProvider = MainThreadSchedulerProvider()
) {

    fun execute(request: REQUEST): Flowable<RESPONSE> {
        return createObservable(request)
            .subscribeOn(subscribeOnScheduler.provideSchedulerProvider())
            .observeOn(observeOnScheduler.provideSchedulerProvider())
    }

    protected abstract fun createObservable(request: REQUEST): Flowable<RESPONSE>
}