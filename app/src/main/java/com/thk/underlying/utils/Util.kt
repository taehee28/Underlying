package com.thk.underlying.utils

import android.util.Log

inline fun <reified T> T.logd(message: String) =
    Log.d(T::class.simpleName, message)