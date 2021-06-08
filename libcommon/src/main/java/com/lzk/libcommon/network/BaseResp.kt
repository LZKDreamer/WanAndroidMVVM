package com.lzk.libcommon.network

/**
 * @Author: LiaoZhongKai
 * @Date: 2021/6/7 17:45
 * @Description:
 */
data class BaseResp<T>(val code: Int,val msg: String,val data: T?)
