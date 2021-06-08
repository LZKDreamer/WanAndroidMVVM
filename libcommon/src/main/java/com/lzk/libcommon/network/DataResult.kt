package com.lzk.libcommon.network

import kotlin.Exception
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * @Author: LiaoZhongKai
 * @Date: 2021/6/7 17:17
 * @Description:
 */

sealed class DataResult<out T>{
    data class Success<out T>(val data: T): DataResult<T>()

    data class Error(val exception: Exception): DataResult<Nothing>()
}

@OptIn(ExperimentalContracts::class)
inline fun <R>DataResult<R>.onSuccess(action: R.() -> Unit): DataResult<R>{
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }

    if (this is DataResult.Success){
        action.invoke(this.data)
    }
    return this
}

@OptIn(ExperimentalContracts::class)
inline fun <R>DataResult<R>.onFailure(action: (e: Exception) -> Unit): DataResult<R>{
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }

    if (this is DataResult.Error){
        action.invoke(exception)
    }
    return this
}