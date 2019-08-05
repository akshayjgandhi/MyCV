package com.akshaygandhi.mycvapplication.util

import android.os.Environment
import androidx.test.runner.screenshot.BasicScreenCaptureProcessor
import java.io.File

class MyScreenCaptureProcessor(parentFolderPath: String) : BasicScreenCaptureProcessor() {

    init {
        this.mDefaultScreenshotPath = File(
            File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                ""
            ).absolutePath,
            "/$parentFolderPath"
        )
    }

    override fun getFilename(prefix: String): String = prefix
}