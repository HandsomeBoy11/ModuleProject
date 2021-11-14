package com.wj.basemudule.utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wj.basemudule.MyApplication;
import com.wj.basemudule.RouterConstant;

import java.util.Set;

/**
 * @author wangjun
 * @date 2021/9/1 14:36
 * @Des :
 */
public class RouterMap {
    /**
     * 推送与部分h5协议 解析 url使用uiRouter
     */
    public static boolean openUrl(@Nullable Context context, @Nullable String url) {
        return openUrl(context, url, null);
    }
    public static boolean openUrl(@Nullable Context context, @Nullable String url, @Nullable Bundle bundle) {
        Logger.INSTANCE.i( url);
        if (url == null || TextUtils.isEmpty(url)) {
            return false;
        }

        //做跳转兼容
//        url = compatible(url);

        Uri intentUri = Uri.parse(url);
        String scheme = intentUri.getScheme();
        if (TextUtils.isEmpty(scheme)) {
            Logger.INSTANCE.e("scheme must not be null (url: %s)  url="+url);
            return false;
        }
        if (TextUtils.equals(scheme, RouterConstant.SCHEME_HTTP) || TextUtils.equals(scheme, RouterConstant.SCHEME_HTTPS)) {
            hookH5Activity(context, url, bundle);
            return true;
        }
        if (!TextUtils.equals(scheme, RouterConstant.SCHEME)) {
            Logger.INSTANCE.e("scheme [%s] is error (url: %s)  scheme= "+scheme+"   url="+ url);
            return false;
        }

        String path = intentUri.getEncodedPath();
        if (TextUtils.isEmpty(path)) {
            Logger.INSTANCE.e("path must not be null");
            return false;
        }

        Set<String> keys = intentUri.getQueryParameterNames();

        if (!keys.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            for (String key : keys) {
                String parameter = intentUri.getQueryParameter(key);
                bundle.putString(key, parameter);
            }
        }
        return openUri(context, path, bundle);
    }

    private static void hookH5Activity(Context context, String url, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString(RouterConstant.Params.PARAMS_URL, url);
        openUri(context,  RouterConstant.Path.GO_H5, bundle);
    }

    /**
     * 使用uiRouter
     */
    public static boolean openUri(@Nullable Context context, String path) {
        return openUri(context,  path, null);
    }

    /**
     * 使用uiRouter
     */
    public static boolean openUri(@Nullable Context context, String path, @Nullable Bundle bundle) {
        // 输出跳转路径。
        context = getContext(context);
        printUrl(context.getClass().getSimpleName(), path, bundle);
        return openUri(context,  path, bundle, 0);
    }

    /**
     * 使用uiRouter
     */
    public static boolean openUri(@Nullable Context context, String path, @Nullable Bundle bundle, int requestCode) {
        Activity activity = getContext(context);
        if(activity!=null){
            ARouter.getInstance().build(path).with(bundle).navigation(activity,requestCode);
        }else{
            ARouter.getInstance().build(path).with(bundle).navigation();
        }
        return false;
    }

    /**
     * 输出url 方便观测
     *
     * @param refClassName 对应的Acitivity实例类
//     * @param host         host
     * @param path         path
     * @param bundle       params
     */
    private static void printUrl(String refClassName,  String path, Bundle bundle) {
//        String url = jointUrl(host, path);
        StringBuilder sb = new StringBuilder()
                .append("Router: from ").append(refClassName).append(" -> ")
                .append("url: ").append(path).append('\n')
                .append("bundle -> ").append(bundle);
        Logger.INSTANCE.d(sb.toString());
    }

    /**
     * 对当前的路径进行拼接
     *
     * @param host host
     * @param path path
     * @return host + path
     */
    public static String jointUrl(String host, String path) {
        return RouterConstant.SCHEME + "://" + host + path;
    }

    /**
     * 对当前的路径进行拼接
     *
     * @param host   host
     * @param path   path
     * @param bundle bundle
     * @return host + path + bundle
     */
    public static String jointUrl(String host, String path, Bundle bundle) {
        if (host == null || path == null) {
            return "";
        }
        String url = RouterConstant.SCHEME + "://" + host + path;
        StringBuilder sb = new StringBuilder(url);
        sb.append("?");

        if (bundle != null) {
            for (String s : bundle.keySet()) {
                sb.append(s).append("=").append(bundle.get(s)).append("&");
            }
        }
        return sb.toString();
    }

//    @NonNull
    private static Activity getContext(@Nullable Context context) {
        if(context instanceof Activity){
            return ((Activity) context);
        }else{
            return null;
        }
//        return context == null ? MyApplication.Companion.getApplication() : context;
    }
}
