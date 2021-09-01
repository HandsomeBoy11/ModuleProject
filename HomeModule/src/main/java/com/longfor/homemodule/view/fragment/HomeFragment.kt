package com.longfor.homemodule.view.fragment

import android.content.Intent
import android.os.Bundle
import com.longfor.basemudule.RouterConstant
import com.longfor.basemudule.base.BaseFragment
import com.longfor.basemudule.utils.Logger
import com.longfor.basemudule.utils.RouterMap
import com.longfor.homemodule.R
import com.longfor.homemodule.view.activity.HomeMainActivity
import com.longfor.homemodule.view.activity.HomeSecondActivity
import kotlinx.android.synthetic.main.home_module_fragment_home.*


/**
 *
 *  @author wangjun
 *  @date  2021/8/30 18:19
 *  @Des  :
 *
 */
class HomeFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.home_module_fragment_home

    override fun initView() {
        tvName.setOnClickListener {
            val bundle = Bundle().apply {
                putString("parm1", "abc")
                putString("parm2", "def")
            }
//            this.startActivityForResult(Intent(context,HomeSecondActivity::class.java),100)
            RouterMap.openUri(context, RouterConstant.Path.HOME_SECOND_PATH, bundle, 111)
        }
    }

    override fun initData() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Logger.d(data?.getStringExtra("aa") ?: "asdfghjkl;")
    }
}