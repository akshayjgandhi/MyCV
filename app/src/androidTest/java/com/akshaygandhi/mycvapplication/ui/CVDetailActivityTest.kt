package com.akshaygandhi.mycvapplication.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.akshaygandhi.mycvapplication.R
import com.akshaygandhi.mycvapplication.data.model.*
import org.junit.Rule
import org.junit.Test

class CVDetailActivityTest {

    private lateinit var skillList: List<Skill>
    private lateinit var workList: List<Work>
    private lateinit var cvDataModel: CVDataModel
    private lateinit var referenceList: List<Reference>
    private lateinit var educationList: List<Education>
    private lateinit var basics: Basics
    private lateinit var profileList: List<Profile>

    /**
     * Rules are interceptors which are executed for each test method and are important building
     * blocks of Junit tests.
     */
    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(CVDetailActivity::class.java)


    @Test
    fun getCV_showsCVDetail() {
        profileList = listOf(Profile("", "", "Test"))
        basics = Basics("", "", Location("", ""), "", profileList, "", "")
        educationList = listOf(Education("", "", "", "", "Software"))
        referenceList = listOf(Reference("Test", ""))
        skillList = listOf(Skill(listOf(""), "Test"))
        workList = listOf(Work("", "", listOf(""), "Test", "", "", ""))
        cvDataModel = CVDataModel(basics, educationList, referenceList, skillList, workList)

        cvDetailScreen {
            //no user input required
        } setCVDetails {
            isDataShown()
            screenshot("Success")
        }
    }

    @Test
    fun checkViewsDisplay() {
        Espresso.onView(withId(R.id.name)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.label)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.email)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.website)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.network_profile)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.summary)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.skills)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.work)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.education)).check(matches(isDisplayed()))


    }

}