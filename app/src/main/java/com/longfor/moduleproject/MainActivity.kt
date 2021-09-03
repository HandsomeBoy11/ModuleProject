package com.longfor.moduleproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.longfor.basemudule.base.BaseActivity
import com.longfor.basemudule.utils.RxJavaUtils
import com.longfor.homemodule.view.fragment.HomeFragment
import com.longfor.homemodule.view.fragment.MyFragment
import io.reactivex.Flowable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity() {
    private val fragments = ArrayList<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragments.add(HomeFragment())
        fragments.add(MyFragment())
        initView()
//        homeTv.performClick()
        switchFragment(0)
        startLoadView()

    }

    private fun startLoadView() {
        loading()
        Flowable.intervalRange(1, 11, 0, 1, TimeUnit.SECONDS)
            .compose(RxJavaUtils.flowableToMain())
            .doOnNext {
                loadingProgress(it.toInt() * 10)
            }
            .doOnComplete {
                hideLoading()
                noDataPage()
            }.subscribe().addDispose()

    }

    override fun onReTry() {
        loading()
        Flowable.intervalRange(1, 6, 0, 1, TimeUnit.SECONDS)
            .compose(RxJavaUtils.flowableToMain())
            .doOnNext {
                loadingProgress(it.toInt() * 20)
            }
            .doOnComplete {
                hideLoading()
                successPage()
            }.subscribe().addDispose()

    }

    private fun initView() {
        setTitleBar()
        homeTv.setOnClickListener {
            switchFragment(0)
            startLoadView()
        }
        myTv.setOnClickListener {
            switchFragment(1)
            startLoadView()
        }
    }

    private fun switchFragment(index: Int) {
        homeTv.isSelected = 0 == index
        myTv.isSelected = 1 == index
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragments[index])
            .commit()
    }

}