package com.wj.basemudule

import android.util.Log
import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.wj.basemudule.utils.Logger

/**
 *
 *  @author wangjun
 *  @date  2021/8/31 17:38
 *  @Des  :
 *
 */
open class MyApplication : MultiDexApplication() {
    companion object {
        var application: MyApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        Log.d("wj====?", "εε§εδΊ")
        if (BuildConfig.DEBUG) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
            Logger.isDebug = true
        }
        ARouter.init(this)
    }

}