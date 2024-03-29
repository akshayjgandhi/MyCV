package com.akshaygandhi.mycvapplication.util

import android.util.Log
import androidx.test.runner.screenshot.Screenshot
import java.lang.Exception


open class BaseRobot {
    fun screenshot(name: String) {
        takeScreenshot("", name)
    }
}

fun takeScreenshot(parentFolderPath: String = "MyCV", name: String) {
    val screenCapture = Screenshot.capture()
    val processors = setOf(MyScreenCaptureProcessor(parentFolderPath))

    try {
        screenCapture.apply {
            setName(name)
            process(processors)
            Log.d("Screenshots", "Screen shot saved with name = $name")
        }
    } catch (ex: Exception) {
        Log.e("Screenshots", "Could not take the screenshot", ex)
    }
}