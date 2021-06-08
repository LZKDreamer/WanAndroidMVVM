package com.lzk.libcommon.network

/**
 * @Author: LiaoZhongKai
 * @Date: 2021/6/8 10:53
 * @Description:
 */

data class ApiException(val code: Int,val msg: String): RuntimeException()

class DataNullException: RuntimeException()