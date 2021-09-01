package com.longfor.moduleproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.longfor.basemudule.RouterConstant
import com.longfor.basemudule.base.BaseActivity
import com.longfor.basemudule.utils.Logger
import com.longfor.basemudule.utils.RouterMap
import com.longfor.homemodule.view.fragment.HomeFragment
import com.longfor.homemodule.view.fragment.MyFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {
    private val fragments = ArrayList<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragments.add(HomeFragment())
        fragments.add(MyFragment())
        initView()
        homeTv.performClick()
    }

    private fun initView() {
        homeTv.setOnClickListener {
            switchFragment(0)
        }
        myTv.setOnClickListener {
            switchFragment(1)
        }
    }

    private fun switchFragment(index: Int) {
        homeTv.isSelected = 0 == index
        myTv.isSelected = 1 == index
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragments[index])
            .commit()
    }

}