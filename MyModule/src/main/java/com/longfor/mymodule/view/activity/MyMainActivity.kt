package com.longfor.homemodule.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.longfor.homemodule.view.fragment.MyFragment
import com.longfor.mymodule.R

/**
 *
 *  @author wangjun
 *  @date  2021/8/30 18:25
 *  @Des  :
 *
 */
class MyMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_module_activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, MyFragment()).commit()
    }
}