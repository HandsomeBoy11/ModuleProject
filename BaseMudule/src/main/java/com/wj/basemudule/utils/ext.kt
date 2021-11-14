package com.wj.basemudule.utils

import android.view.View

/**
 *
 *  @author wangjun
 *  @date  2021/8/31 9:45
 *  @Des  :
 *
 */

fun View.serVisible(boolean: Boolean) {
    if (boolean) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}