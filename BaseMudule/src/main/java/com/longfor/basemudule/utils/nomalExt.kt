package com.longfor.basemudule.utils

import android.util.Log
import android.util.TypedValue
import android.view.View
import com.google.gson.Gson
import com.longfor.basemudule.MyApplication
import java.lang.reflect.Type

private val metrics = MyApplication.application!!.resources.displayMetrics

val Float.dp: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, metrics)

val Int.dp: Int
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics).toInt()
val Int.sp: Int
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), metrics).toInt()

val Number.px: Number
    get() = this

val Number.dp2px: Float
    get() = this.toFloat() * metrics.density + 0.5f

val Number.sp2px: Float
    get() = this.toFloat() * metrics.scaledDensity + 0.5f

/**
 * 判空、catch异常的 parseInt
 * @param defaultValue 无法转换Int时的默认返回值
 * @param radix Parses the string argument as a signed integer in the radix.
 *
 * @see String.toIntOrNull
 */
fun String?.toIntSafe(defaultValue: Int, radix: Int = 10): Int {
    return try {
        this?.toIntOrNull(radix) ?: defaultValue
    } catch (e: Throwable) {
        Log.e("wj==>", "parse Int failed")
        defaultValue
    }
}

fun String?.toLongSafe(defaultValue: Long): Long {
    return try {
        this?.toLongOrNull() ?: defaultValue
    } catch (e: Throwable) {
        Log.e("wj==>", "parse Int failed")
        defaultValue
    }
}

/**
 * 判空、catch异常的 parseInt
 * @param radix Parses the string argument as a signed integer in the radix.
 *
 * @see String.toIntOrNull
 */
fun String?.toIntSafe(defaultValue: Int? = null, radix: Int = 10): Int? {
    return try {
        this?.toIntOrNull(radix)
    } catch (e: Throwable) {
        Log.e("wj==>", "parse Int failed")
        defaultValue
    }
}

/**
 * * null - ""
 * @see Any.toString
 */
fun Any?.toStringOrEmpty(): String {
    if (this == null)
        return ""
    return this.toString()
}

val gsonInstance by lazy { Gson() }

fun <K, V> jsonOf(vararg pairs: Pair<K, V>): String {
    return try {
        pairs.toMap().toJson()
    } catch (e: Exception) {
        Log.e("wj==>", "jsonOf error")
        "{}"
    }
}

fun Any?.toJson(): String {
    return try {
        gsonInstance.toJson(this)
    } catch (e: Exception) {
        Log.e("wj==>", "$this toJson error")
        "{}"
    }
}

fun <T> String?.fromJson(clazz: Class<T>): T? =
    try {
        gsonInstance.fromJson(this, clazz)
    } catch (e: Exception) {
        Log.e("wj==>", "$this fromJson error")
        null
    }

fun <T> String?.fromJson(typeOfT: Type): T? =
    try {
        gsonInstance.fromJson(this, typeOfT)
    } catch (e: Exception) {
        Log.e("wj==>", "$this fromJson error")
        null
    }
/**
 * 设置View的显示与隐藏
 */
fun View.setVisibility(visibility: Boolean) {
    if (visibility) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}