package com.akshaygandhi.mycvapplication.ui

import com.akshaygandhi.mycvapplication.app.UseCase
import com.akshaygandhi.mycvapplication.data.model.CVDataModel
import com.akshaygandhi.mycvapplication.rx.IoThreadScheduler
import com.akshaygandhi.mycvapplication.rx.MainThreadScheduler
import com.akshaygandhi.mycvapplication.rx.SchedulerProvider
import io.reactivex.Flowable
import javax.inject.Inject

class CVDetailUseCase @Inject constructor(
    private val cVDetailDataRepository: CVDetailDataRepository,
    @IoThreadScheduler subscribeOnScheduler: SchedulerProvider,
    @MainThreadScheduler observeOnScheduler: SchedulerProvider
) : UseCase<String, CVDetailInfo>(
    subscribeOnScheduler,
    observeOnScheduler
) {
    override fun createObservable(request: String): Flowable<CVDetailInfo> {
        return cVDetailDataRepository.getCVDetail(request).map { t: CVDataModel ->
            CVDetailInfo(t).apiToUiMapper()
        }
    }
}