package com.akshaygandhi.mycvapplication.ui

import com.akshaygandhi.mycvapplication.data.model.Basics
import com.akshaygandhi.mycvapplication.data.model.CVDataModel
import com.akshaygandhi.mycvapplication.data.model.Location
import com.akshaygandhi.mycvapplication.data.model.Profile
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CVDetailInfoTest {

    private lateinit var cvDetailInfo: CVDetailInfo
    private lateinit var cvDataModel: CVDataModel

    private lateinit var testObject: CVDetailInfo

    @Before
    fun setUp() {
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

        testObject = CVDetailInfo(cvDataModel).apiToUiMapper()
    }

    @Test
    @Throws(Exception::class)
    fun testModel() {
        assertEquals(cvDetailInfo, testObject)
    }
}