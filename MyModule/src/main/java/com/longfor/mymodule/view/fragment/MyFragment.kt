package com.longfor.homemodule.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.longfor.mymodule.R


/**
 *
 *  @author wangjun
 *  @date  2021/8/30 18:19
 *  @Des  :
 *
 */
class MyFragment :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.my_module_fragment_home,container,false)
//        return super.onCreateView(inflater, container, savedInstanceState)
    }
}