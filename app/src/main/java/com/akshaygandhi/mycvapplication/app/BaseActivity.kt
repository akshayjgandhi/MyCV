package com.akshaygandhi.mycvapplication.app

import android.os.Bundle

/**
 * Abstract Base activity to handle common logic.
 */

abstract class BaseActivity<T : BaseViewModel> : BaseView<T>, RootActivity() {

    protected lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getAssociatedViewModel()
    }
}