package com.akshaygandhi.mycvapplication.ui

import com.akshaygandhi.mycvapplication.data.CVApiService
import com.akshaygandhi.mycvapplication.data.model.Basics
import com.akshaygandhi.mycvapplication.data.model.CVDataModel
import com.akshaygandhi.mycvapplication.data.model.Location
import com.akshaygandhi.mycvapplication.data.model.Profile
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CVDetailDataRepositoryTest {

    @Mock
    lateinit var cvApiService: CVApiService

    private lateinit var testObject: CVDetailDataRepository

    private lateinit var cvDataModel: CVDataModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testObject = CVDetailDataRepository(cvApiService)

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

    }

    @Test
    @Throws(Exception::class)
    fun testGetCVDetailInfoError() {
        val throwable = Throwable("Connection error")

        whenever(cvApiService.getCV(""))
            .thenReturn(Flowable.error(throwable))

        val testObserver = testObject.getCVDetail("").test()

        testObserver.assertError(throwable)
    }

    @Test
    @Throws(Exception::class)
    fun testGetCVDetailInfo() {
        whenever(cvApiService.getCV(""))
            .thenReturn(Flowable.just(cvDataModel))

        val testObserver = testObject.getCVDetail("").test()

        testObserver.assertNoErrors()

        testObserver.assertValue { result: CVDataModel ->
            cvDataModel.basics.email == result.basics.email
        }

        testObserver.assertValue { result: CVDataModel ->
            cvDataModel.basics.location.city == result.basics.location.city
        }

        testObserver.assertValue { result: CVDataModel ->
            cvDataModel.basics.profiles[0].username == result.basics.profiles[0].username
        }

    }
}