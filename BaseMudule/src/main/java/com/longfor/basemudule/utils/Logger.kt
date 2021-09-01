package com.longfor.basemudule.utils

import android.util.Log

/**
 *
 *  @author wangjun
 *  @date  2021/9/1 14:55
 *  @Des  :
 *
 */
object Logger {

    var isDebug: Boolean = false

    fun i(msg: String) {
        if (isDebug) {
            Log.i("Logger::", msg)
        }
    }

    fun d(msg: String) {
        if (isDebug) {
            Log.d("Logger::", msg)
        }
    }

    fun e(msg: String) {
        if (isDebug) {
            Log.e("Logger::", msg)
        }
    }
}