package com.longfor.basemudule.utils

import android.app.Activity
import kotlin.system.exitProcess

/**
 *
 *  @author wangjun
 *  @date  2021/9/3 10:23
 *  @Des  :
 *
 */
object ActivityContainerUtil {
    private val activityContainer = arrayListOf<Activity>()

    /**
     * 添加 Activity
     * @param activity Activity
     */
    fun addActivity(activity: Activity) {
        activityContainer.add(activity)
    }

    fun removeActivity(activity: Activity) {
        if (activityContainer.contains(activity)) {
            activityContainer.remove(activity)
        }
    }

    /**
     * 退出应用
     */
    fun exitApp() {
        activityContainer.clear()
        exitProcess(0)
    }
}