package com.longfor.homemodule.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.longfor.homemodule.R
import com.longfor.homemodule.view.fragment.HomeFragment

/**
 *
 *  @author wangjun
 *  @date  2021/8/30 18:25
 *  @Des  :
 *
 */
class HomeMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_module_activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, HomeFragment()).commit()
    }
}