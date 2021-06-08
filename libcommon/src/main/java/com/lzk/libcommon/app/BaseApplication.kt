package com.lzk.libcommon.app

import android.app.Application
import com.lzk.libcommon.network.net.RxHttpManager

/**
 * @Author: LiaoZhongKai
 * @Date: 2021/6/7 15:56
 * @Description:
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RxHttpManager.init()
    }

}