package com.akshaygandhi.mycvapplication.utils

import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate(): String {
    val originalDate = SimpleDateFormat("yyyy-MM-DD", Locale.getDefault()).parse(this)

    return SimpleDateFormat("MMM yyyy", Locale.getDefault()).format(originalDate)
}