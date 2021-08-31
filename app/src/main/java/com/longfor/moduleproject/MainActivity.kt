package com.longfor.moduleproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.longfor.homemodule.view.fragment.HomeFragment
import com.longfor.homemodule.view.fragment.MyFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private val fragments= ArrayList<Fragment>()
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
            switchFragment(fragments[0])
        }
        myTv.setOnClickListener {
            switchFragment(fragments[1])

        }
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()
    }
}