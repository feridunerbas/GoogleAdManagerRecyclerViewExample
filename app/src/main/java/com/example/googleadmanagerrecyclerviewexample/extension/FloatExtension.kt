package com.example.googleadmanagerrecyclerviewexample.extension

import android.content.Context
import android.util.DisplayMetrics

fun Float.toPixels(context: Context): Float{
    return this * context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
}

fun Float.toDp(context: Context): Float{
    return this / (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}