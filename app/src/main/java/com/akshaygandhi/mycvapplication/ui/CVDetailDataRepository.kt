package com.akshaygandhi.mycvapplication.ui

import com.akshaygandhi.mycvapplication.data.CVApiService
import com.akshaygandhi.mycvapplication.data.model.CVDataModel
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CVDetailDataRepository
@Inject constructor(
    private val cVApiService: CVApiService
) {

    fun getCVDetail(userId: String): Flowable<CVDataModel> {
        return cVApiService.getCV(userId)
    }
}