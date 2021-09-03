package com.longfor.basemudule.utils

import android.view.View

/**
 * 防重点击。
 * @param clickListener 处理点击事件
 */
fun View.setRepeatClick(clickListener: (v: View) -> Unit) {
    setOnClickListener(object : OnRepeatClickListener() {
        override fun onRepeatClick(v: View) {
            clickListener(v)
        }
    })
}

/**
 * 全局防重点击
 * @param clickListener 处理点击事件
 */
fun View.setGlobalRepeatClick(clickListener: (v: View) -> Unit) {
    setOnClickListener(object : OnGlobalRepeatClickListener() {
        override fun onRepeatClick(v: View) {
            clickListener(v)
        }
    })
}
