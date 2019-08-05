package com.akshaygandhi.mycvapplication.rx

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
object RxModule {

    @Provides
    @JvmStatic
    @Singleton
    @MainThreadScheduler
    fun provideMainThreadSchedulerProvider(): SchedulerProvider {
        return MainThreadSchedulerProvider()
    }

    @Provides
    @JvmStatic
    @Singleton
    @IoThreadScheduler
    fun provideIoSchedulerProvider(): SchedulerProvider {
        return IoThreadSchedulerProvider()
    }

    @Provides
    @JvmStatic
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}