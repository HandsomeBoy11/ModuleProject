package com.longfor.homemodule.view.activity

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.longfor.basemudule.RouterConstant
import com.longfor.basemudule.base.BaseActivity
import com.longfor.basemudule.utils.Logger
import com.longfor.homemodule.R
import kotlinx.android.synthetic.main.home_module_activity_home_second.*

/**
 *
 *  @author wangjun
 *  @date  2021/8/31 17:33
 *  @Des  :
 *
 */
@Route(path = RouterConstant.Path.HOME_SECOND_PATH)
class HomeSecondActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_module_activity_home_second)
        val extras = intent?.extras
        val parm1 = extras?.getString("parm1")
        val parm2 = extras?.getString("parm2")
        Logger.d("extras= $extras parm1= $parm1  parm2= $parm2")

        tvName.setOnClickListener {
            val intent = Intent()
            intent.putExtra("aa", "aa")
            setResult(100, intent)
            finish()
        }
    }
}