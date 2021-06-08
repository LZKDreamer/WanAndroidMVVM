package com.lzk.libcommon.network.net

import com.lzk.libcommon.network.ApiException
import com.lzk.libcommon.network.DataNullException
import com.lzk.libcommon.network.DataResult
import com.lzk.libcommon.network.ExceptionHandler
import rxhttp.IAwait

/**
 * @Author: LiaoZhongKai
 * @Date: 2021/6/8 11:35
 * @Description:
 */

suspend fun <T: Any> IAwait<T>.request(): DataResult<T> {
    var result: DataResult<T> = DataResult.Empty
    kotlin.runCatching {
        this.await()
    }.onSuccess {
        result = DataResult.Success(it)
    }.onFailure {
        result = when(it){
            is ApiException -> {
                DataResult.Error(it.code, it.msg)
            }
            is DataNullException -> {
                DataResult.Empty
            }
            else ->{
                DataResult.Failure(ExceptionHandler.getErrorMessage(it))
            }
        }
    }
    return result
}