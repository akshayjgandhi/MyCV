package com.akshaygandhi.mycvapplication.di

import com.akshaygandhi.mycvapplication.ui.CVDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    internal abstract fun bindCVDetailActivity(): CVDetailActivity
}