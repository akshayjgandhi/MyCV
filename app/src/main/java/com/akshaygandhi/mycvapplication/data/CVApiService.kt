package com.akshaygandhi.mycvapplication.data

import com.akshaygandhi.mycvapplication.data.model.CVDataModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API service to fetch CV data from server
 */
interface CVApiService {

    @GET("{userId}/6fb9f000d613776e3b62662660a8ef47/raw/0917be80ebfe1894a6353f164a2790882760d207/resume.json")
    fun getCV(@Path("userId") userId: String): Flowable<CVDataModel>
}