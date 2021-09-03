package com.longfor.basemudule.utils

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


/**
 * Created by luojilab_sufun
 * @author luojilab_sufun
 * @createData 2018/3/29
 * @description 用于简化线程的切换,IO--->Main
 */
object RxJavaUtils {

    private val frequentlyUseExecutor by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        ThreadPoolExecutor(
                8,
                8,
                180L,
                TimeUnit.SECONDS,
                LinkedBlockingQueue<Runnable>())
                .apply {
                    allowCoreThreadTimeOut(true)
                }
    }

    @JvmStatic
    fun <T> observableToMain(): ObservableTransformer<T, T> {

        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    @JvmStatic
    fun <T> flowableToMain(): FlowableTransformer<T, T> {

        return FlowableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 订阅自定义线程池切换到主线程
     * 场景：一个事件被大量多处订阅使用Schedulers.io()会造成cache线程池瞬间创建大量线程
     * 解决方案：自定义限制线程池大小的无界缓冲队列线程池
     * warning：仅适用于轻量级任务，禁止使用此调度器做网络请求、timer、sample、delay等操作
     */
    fun <T> switchCustomTreadToMain(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream.subscribeOn(getCustomWorkScheduler())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 订阅io线程池切换到主线程
     */
    fun <T> switchIOToMain(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 订阅Rxjava的单线程池切换到主线程
     * 适用场景：同时发出的多个事件有严格的前后顺序
     */
    fun <T> switchSingleToMain(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.single())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 获取自定义线程调度器
     * 自定义无界缓冲队列线程池
     * warning：仅适用于轻量级任务，禁止使用此调度器做网络请求、timer、delay、sample等操作
     */
    fun getCustomWorkScheduler(): Scheduler {
        return Schedulers.from(frequentlyUseExecutor)
    }

    /**
     * 延迟尝试发送
     */
    class RetryWithDelayFlowable(
            /**
             * 最大的尝试次数
             */
            private val maxRetryCount: Int,
            /**
             * 延迟时间单位为秒
             */
            private val retryDelay: Long) : Function<Flowable<out Throwable>, Flowable<*>> {
        /**
         * 当前已经尝试过的次数
         */
        private var retryCount: Int = 0

        init {
            this.retryCount = 0
        }

        override fun apply(attempts: Flowable<out Throwable>): Flowable<*> {
            return attempts.flatMap { throwable ->
                if (++retryCount < maxRetryCount) {
                    attempts.flatMap {
                        Flowable.timer(retryDelay, TimeUnit.SECONDS)
                    }
                } else {
                    Flowable.error(throwable)
                }
            }
        }
    }

    /**
     * 延时处理发送
     */
    class RetryWithDelayObserval(private val maxRetryCount: Int, private val retryDelay: Long) : Function<Observable<out Throwable>, Observable<*>> {
        private var retryCount: Int = 0

        init {
            this.retryCount = 0
        }

        override fun apply(attempts: Observable<out Throwable>): Observable<*> {
            return attempts.flatMap { throwable ->
                if (++retryCount < maxRetryCount) {
                    attempts.flatMap {
                        Observable.timer(retryDelay, TimeUnit.SECONDS)
                    }
                } else {
                    Observable.error(throwable)
                }
            }
        }
    }
}
