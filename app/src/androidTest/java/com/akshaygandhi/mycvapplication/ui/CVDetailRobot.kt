package com.akshaygandhi.mycvapplication.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.akshaygandhi.mycvapplication.R
import com.akshaygandhi.mycvapplication.util.BaseRobot


class CVDetailRobot(private val activity: CVDetailActivity?) : BaseRobot() {


    infix fun setCVDetails(func: Result.() -> Unit): Result {
        Espresso.onView(ViewMatchers.withId(R.id.cv_detail))
            .perform(ViewActions.click())
        return Result().apply { func() }
    }

    class Result : BaseRobot() {

        fun isDataShown() {
            Espresso.onView(ViewMatchers.withId(R.id.cv_detail))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

    }
}

/**
 * Extension function for building CVDetailRobot and asserting expected views are available
 */
fun cvDetailScreen(func: CVDetailRobot.() -> Unit): CVDetailRobot {

    //assert expected views are displayed
    Espresso.onView(ViewMatchers.withId(R.id.cv_detail))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    return CVDetailRobot(null).apply { func() }
}
