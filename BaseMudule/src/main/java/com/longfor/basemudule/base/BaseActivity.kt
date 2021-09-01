package com.longfor.basemudule.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *
 *  @author wangjun
 *  @date  2021/9/1 11:23
 *  @Des  :
 *
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()

    }
}