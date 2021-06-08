package com.lzk.libcommon.network

import android.util.Log
import com.lzk.libcommon.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.ssl.HttpsUtils
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier

/**
 * @Author: LiaoZhongKai
 * @Date: 2021/6/8 14:13
 * @Description:
 */
object RxHttpManager {

    fun init(){
        val sslParams = HttpsUtils.getSslSocketFactory()
        val okClient = OkHttpClient.Builder()
            .callTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .sslSocketFactory(sslParams.sSLSocketFactory,sslParams.trustManager)
            .hostnameVerifier { _, _ -> true }
            .retryOnConnectionFailure(true)
            .followRedirects(false)
            .cookieJar(LocalCookieJar())
            .addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.d("RxHttpManager", "log: $message")
                }

            }).setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        RxHttpPlugins
            .init(okClient)
            .setDebug(BuildConfig.DEBUG)
            .setOnParamAssembly {
                //在这里添加token等公共请求头
                //it.addHeader("Content-Type","application/json")
                it
            }
    }
}