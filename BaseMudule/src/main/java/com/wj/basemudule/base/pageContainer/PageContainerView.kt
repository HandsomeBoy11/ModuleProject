package com.wj.basemudule.base.pageContainer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.transition.TransitionManager
import com.wj.basemudule.R
import com.wj.basemudule.utils.ProgressTransition
import com.wj.basemudule.utils.setRepeatClick
import com.wj.basemudule.utils.setVisibility
import kotlinx.android.synthetic.main.layout_loading.view.*
import kotlinx.android.synthetic.main.layout_no_data.view.*

/**
 *
 *  @author wangjun
 *  @date  2021/9/3 11:06
 *  @Des  : 页面内容容器
 *
 */
class PageContainerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    def: Int = 0
) : FrameLayout(context, attrs, def) {
    companion object {
        const val NO_DATA = "noData"
        const val no_net = "noNet"
        const val LOADING = "loading"
    }

    private val layoutMap = HashMap<String, View?>()
    private var noDataView: View? = null
    private var loadingView: View? = null
    private var contentView: View? = null
    private var isLoading: Boolean = false

    fun addContentView(layoutResID: Int) {
        contentView = LayoutInflater.from(context).inflate(layoutResID, this, false)
        removeAllViews()
        addView(contentView)
    }

    fun addContentView(contentView: View?) {
        removeAllViews()
        addView(contentView)
    }

    fun addNoDataView(@LayoutRes noDataLayout: Int = R.layout.layout_no_data, onReTry: () -> Unit) {
        val noDataMapView = layoutMap[NO_DATA]
        if (noDataMapView == null) {
            noDataView = LayoutInflater.from(context).inflate(noDataLayout, this, false)
            addView(noDataView)
            layoutMap[NO_DATA] = noDataView
        } else {
            addView(noDataMapView)
        }
        llNoData.setRepeatClick {
            onReTry.invoke()
        }
        contentView?.setVisibility(false)
    }

    fun showLoading(@LayoutRes loadingLayout: Int = R.layout.layout_loading) {
        val loadingMapView = layoutMap[LOADING]
        if (loadingMapView == null) {
            loadingView = LayoutInflater.from(context).inflate(loadingLayout, this, false)
            addView(loadingView)
            layoutMap[LOADING] = loadingView
        } else {
            addView(loadingMapView)
        }
        isLoading = true
    }

    fun showLoadingProcess(time: Int) {
        if (isLoading) {
            TransitionManager.beginDelayedTransition(this, ProgressTransition())
            h5_loading_progress_bar.progress = 0.coerceAtLeast(100.coerceAtMost(time))
        }
    }

    fun showSuccessPage() {
        layoutMap.forEach {
            removeView(it.value)
        }
        isLoading = false
        contentView?.setVisibility(true)
    }

    fun hideLoading() {
        layoutMap.forEach {
            removeView(it.value)
        }
        isLoading = false
    }

}