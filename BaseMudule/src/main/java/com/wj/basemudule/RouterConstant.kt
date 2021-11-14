package com.wj.basemudule

/**
 *
 *  @author wangjun
 *  @date  2021/9/1 14:43
 *  @Des  :
 *
 */
object RouterConstant {


    const val HOST_APP: String="wj.gy.app"

    /**
     * scheme http
     */
    const val SCHEME_HTTP = "http"

    /**
     * scheme https
     */
    const val SCHEME_HTTPS = "https"

    /**
     * scheme
     */
    const val SCHEME: String ="wj"

    object Params{
        /**
         * url 参数
         */
        const val PARAMS_URL: String ="params_url"
    }
    object Path{
        const val GO_H5: String ="/"
        const val HOME_SECOND_PATH="/home/homesecond"
    }
}