package com.longfor.basemudule.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.longfor.basemudule.R
import com.longfor.basemudule.base.pageContainer.PageCallBack
import com.longfor.basemudule.utils.ActivityContainerUtil
import com.longfor.basemudule.utils.setVisibility
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_activity_core.*

/**
 *
 *  @author wangjun
 *  @date  2021/9/1 11:23
 *  @Des  :
 *
 */
open class BaseActivity : AppCompatActivity(), PageCallBack {
    private var viewContainer: View? = null
    private val disposableGroup = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityContainerUtil.addActivity(this)
        initViews()

    }

    private fun initViews() {
        viewContainer = LayoutInflater.from(this).inflate(R.layout.activity_activity_core, null)
        setSuperContentView(view = viewContainer!!)
    }

    /**
     * 设置是否显示标题 默认设置显示
     * @param show Boolean
     */
    fun setTitleBar(show: Boolean = true) {
        titleBar.setVisibility(show)
    }

    override fun onDestroy() {
        ActivityContainerUtil.removeActivity(this)
        disposableGroup.dispose()
        super.onDestroy()

    }

    private fun setSuperContentView(view: View) {
        super.setContentView(view)
    }

    override fun setContentView(layoutResID: Int) {
        viewContainer?.let {
            pageContainer.addContentView(layoutResID)
        }
    }

    override fun setContentView(view: View?) {
        viewContainer?.let {
            view?.let { contentView ->
                pageContainer.addContentView(contentView)
            }
        }
    }

    override fun successPage() {
        pageContainer.showSuccessPage()
    }

    override fun noDataPage() {
        pageContainer.addNoDataView {
            onReTry()
        }
    }

    override fun noNetPage() {

    }

    override fun loading() {
        pageContainer.showLoading()
    }

    override fun loadingProgress(time: Int) {
        pageContainer.showLoadingProcess(time)
    }

    override fun hideLoading() {
        pageContainer.hideLoading()
    }

    override fun onReTry() {

    }

    fun Disposable.addDispose() {
        disposableGroup.add(this)
    }


}