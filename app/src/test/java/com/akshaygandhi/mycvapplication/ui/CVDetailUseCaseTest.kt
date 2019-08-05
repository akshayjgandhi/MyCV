package com.akshaygandhi.mycvapplication.ui

import com.akshaygandhi.mycvapplication.data.model.Basics
import com.akshaygandhi.mycvapplication.data.model.CVDataModel
import com.akshaygandhi.mycvapplication.data.model.Location
import com.akshaygandhi.mycvapplication.data.model.Profile
import com.akshaygandhi.mycvapplication.util.TestSchedulerProvider
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CVDetailUseCaseTest {

    @Mock
    lateinit var cvDetailDataRepository: CVDetailDataRepository

    private lateinit var testObject: CVDetailUseCase

    private lateinit var cvDataModel: CVDataModel

    private lateinit var cvDetailInfo: CVDetailInfo

    @Before
    fun setUp() {
        testObject = CVDetailUseCase(
            cvDetailDataRepository,
            TestSchedulerProvider(),
            TestSchedulerProvider()
        )

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
        cvDetailInfo = CVDetailInfo(cvDataModel).apiToUiMapper()


    }

    @Test
    @Throws(Exception::class)
    fun testExecute() {

        whenever(cvDetailDataRepository.getCVDetail(""))
            .thenReturn(Flowable.just(cvDataModel))

        val testObserver = testObject.execute("").test()
        testObserver.assertNoErrors()
        testObserver.assertValue { output: CVDetailInfo -> output == cvDetailInfo }
    }

    @Test
    @Throws(Exception::class)
    fun testExecuteError() {
        val throwable = Throwable("Connection error")

        whenever(cvDetailDataRepository.getCVDetail(""))
            .thenReturn(Flowable.error(throwable))

        val testObserver = testObject.execute("").test()

        testObserver.assertError(throwable)
    }
}