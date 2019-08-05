package com.akshaygandhi.mycvapplication.di

import com.akshaygandhi.mycvapplication.CVDetailApp
import com.akshaygandhi.mycvapplication.rx.RxModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class, RxModule::class, NetworkModule::class, ActivityBindingModule::class, ViewModelModule::class]
)

interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: CVDetailApp)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: CVDetailApp): Builder

        fun build(): AppComponent
    }
}