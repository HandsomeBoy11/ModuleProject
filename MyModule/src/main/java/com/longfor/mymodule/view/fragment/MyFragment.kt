package com.longfor.homemodule.view.fragment

import com.alibaba.android.arouter.launcher.ARouter
import com.longfor.basemudule.RouterConstant
import com.longfor.basemudule.base.BaseFragment
import com.longfor.mymodule.R
import kotlinx.android.synthetic.main.my_module_fragment_home.*


/**
 *
 *  @author wangjun
 *  @date  2021/8/30 18:19
 *  @Des  :
 *
 */
class MyFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.my_module_fragment_home

    override fun initView() {
        tvName.setOnClickListener {
            ARouter.getInstance().build(RouterConstant.Path.HOME_SECOND_PATH).navigation();
        }
    }

    override fun initData() {
    }
}