package com.akshaygandhi.mycvapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akshaygandhi.mycvapplication.app.BaseViewModelImpl
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CVDetailViewModelImpl @Inject constructor(
    private val cVDetailUseCase: CVDetailUseCase,
    compositeDisposable: CompositeDisposable
) : BaseViewModelImpl(compositeDisposable), CVDetailViewModel {

    val cvDetailLiveData = MutableLiveData<CVDetailInfo>()

    override fun getCVDetail(userId: String): LiveData<CVDetailInfo> {
        manageActionDisposables(cVDetailUseCase
            .execute(userId).subscribe(
                { t: CVDetailInfo? ->
                    cvDetailLiveData.value = t
                },
                { t: Throwable? ->
                    t?.printStackTrace()
                    cvDetailLiveData.value = null
                }

            ))
        return cvDetailLiveData
    }

}