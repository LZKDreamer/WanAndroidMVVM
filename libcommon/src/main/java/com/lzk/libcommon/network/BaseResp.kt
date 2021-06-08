package com.lzk.libcommon.network

import androidx.annotation.Keep

/**
 * @Author: LiaoZhongKai
 * @Date: 2021/6/7 17:45
 * @Description:
 */
@Keep
data class BaseResp<T>(var errorCode: Int, var errorMsg: String, var data: T?){
    companion object{
        const val SUCCESS_CODE = 0
    }

    fun isSuccess(): Boolean = errorCode == SUCCESS_CODE
}
