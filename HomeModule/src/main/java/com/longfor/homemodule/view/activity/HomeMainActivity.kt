package com.longfor.homemodule.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.longfor.basemudule.BuildConfig
import com.longfor.basemudule.base.BaseActivity
import com.longfor.basemudule.utils.Logger
import com.longfor.homemodule.R
import com.longfor.homemodule.view.fragment.HomeFragment

/**
 *
 *  @author wangjun
 *  @date  2021/8/30 18:25
 *  @Des  :
 *
 */
class HomeMainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.home_module_activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, HomeFragment()).commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Logger.d(data?.getStringExtra("aa")?:"asdfghjkl;")
    }
}