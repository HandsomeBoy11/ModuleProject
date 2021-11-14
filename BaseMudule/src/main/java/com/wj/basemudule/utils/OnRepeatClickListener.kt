package com.wj.basemudule.utils

import android.view.View

/**
 * 两次点击按钮之间的点击间隔不能少于500毫秒
 */
private const val MIN_CLICK_DELAY_TIME = 500000000

/**
 * 防重复点击的监听器
 */
abstract class OnRepeatClickListener : View.OnClickListener {

    /**
     * 控件上次点击时间
     */
    private var lastClickTime: Long = 0

    /**
     * 避免重复点击
     *
     * @param v view
     */
    abstract fun onRepeatClick(v: View)
    override fun onClick(v: View) {
        if (!isFastClick) {
            onRepeatClick(v)
        }
    }

    // 超过点击间隔后再将lastClickTime重置为当前点击时间
    private val isFastClick: Boolean
        get() {
            val curClickTime = System.nanoTime()
            val interval = curClickTime - lastClickTime
            if (interval in 1 until MIN_CLICK_DELAY_TIME) {
                // 超过点击间隔后再将lastClickTime重置为当前点击时间
                return true
            }
            lastClickTime = curClickTime
            return false
        }

}

/**
 * 全局控件上次点击时间
 */
private var lastGlobalClickTime: Long = 0

/**
 * 全局防重复点击的监听器（多控件500ms内仅能点击一次）
 */
abstract class OnGlobalRepeatClickListener : View.OnClickListener {

    /**
     * 避免重复点击
     *
     * @param v view
     */
    abstract fun onRepeatClick(v: View)
    override fun onClick(v: View) {
        if (!isFastClick) {
            onRepeatClick(v)
        }
    }

    // 超过点击间隔后再将lastClickTime重置为当前点击时间
    private val isFastClick: Boolean
        get() {
            val curClickTime = System.nanoTime()
            val interval = curClickTime - lastGlobalClickTime
            if (interval in 1 until MIN_CLICK_DELAY_TIME) {
                // 超过点击间隔后再将lastClickTime重置为当前点击时间
                return true
            }
            lastGlobalClickTime = curClickTime
            return false
        }

}
