package com.akshaygandhi.mycvapplication.di

import androidx.lifecycle.ViewModelProvider
import com.akshaygandhi.mycvapplication.app.BaseViewModel
import com.akshaygandhi.mycvapplication.ui.CVDetailViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CVDetailViewModelImpl::class)
    protected abstract fun cVDetailViewModel(cVDetailViewModelImpl: CVDetailViewModelImpl): BaseViewModel
}