package com.lzk.libcommon.app

import android.app.Application
import com.drake.brv.utils.BRV
import com.lzk.libcommon.BR
import com.lzk.libcommon.network.net.RxHttpManager

/**
 * @Author: LiaoZhongKai
 * @Date: 2021/6/7 15:56
 * @Description:
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initRxHttp()
        initBRV()
    }

    private fun initRxHttp(){
        RxHttpManager.init()
    }

    private fun initBRV(){
    }

}