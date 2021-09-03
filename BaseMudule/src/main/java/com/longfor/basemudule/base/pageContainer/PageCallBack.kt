package com.longfor.basemudule.base.pageContainer

/**
 *
 *  @author wangjun
 *  @date  2021/9/3 13:57
 *  @Des  :
 *
 */
interface PageCallBack {
    fun successPage()
    fun noDataPage()
    fun noNetPage()
    fun loading()
    fun loadingProgress(time:Int)
    fun hideLoading()
    fun onReTry()
}