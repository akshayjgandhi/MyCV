package com.akshaygandhi.mycvapplication.ui

import com.akshaygandhi.mycvapplication.data.model.Basics
import com.akshaygandhi.mycvapplication.data.model.CVDataModel
import com.akshaygandhi.mycvapplication.data.model.Location
import com.akshaygandhi.mycvapplication.data.model.Profile
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CVDetailViewModelImplTest {

    private lateinit var cvDetailInfoMock: CVDetailInfo
    private lateinit var cvDataModel: CVDataModel

    @Mock
    lateinit var cvDetailUseCase: CVDetailUseCase

    private lateinit var testObject: CVDetailViewModelImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testObject = CVDetailViewModelImpl(cvDetailUseCase, CompositeDisposable())

        val profile = Profile(
            "test",
            "http://www.test.com",
            "xyz"
        )

        val location = Location(
            "San Francisco",
            "US"
        )

        val basics = Basics("test@test.com", "", location, "", listOf(profile), "", "")

        cvDataModel = CVDataModel(
            basics, listOf(), listOf(), listOf(), listOf()

        )
        cvDetailInfoMock = CVDetailInfo(cvDataModel).apiToUiMapper()


    }

    @Test
    fun testGetCVDetail() {
        whenever(cvDetailUseCase.execute(""))
            .thenReturn(Flowable.just(cvDetailInfoMock))

        testObject.getCVDetail("")

        Assert.assertEquals(testObject.cvDetailLiveData.value, cvDetailInfoMock)
    }
}