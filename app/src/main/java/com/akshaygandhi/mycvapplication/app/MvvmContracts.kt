package com.akshaygandhi.mycvapplication.app

import io.reactivex.disposables.Disposable

interface BaseView<T : BaseViewModel> {
    fun getAssociatedViewModel(): T
}

interface BaseViewModel {
    fun manageActionDisposables(vararg disposable: Disposable)
}