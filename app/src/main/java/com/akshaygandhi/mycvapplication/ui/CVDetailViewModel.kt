package com.akshaygandhi.mycvapplication.ui

import androidx.lifecycle.LiveData
import com.akshaygandhi.mycvapplication.app.BaseViewModel

interface CVDetailViewModel : BaseViewModel {

    fun getCVDetail(userId: String): LiveData<CVDetailInfo>
}