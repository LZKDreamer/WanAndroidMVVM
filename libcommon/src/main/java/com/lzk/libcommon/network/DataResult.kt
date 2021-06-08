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

    data class Failure(val msg: String): DataResult<Nothing>()

    data class Error(val code: Int,val msg: String): DataResult<Nothing>()

    object Empty: DataResult<Nothing>()
}

/**
 * 网络请求和接口均返回成功
 */
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

/**
 * 网络请求失败
 */
@OptIn(ExperimentalContracts::class)
inline fun <R>DataResult<R>.onFailure(action: (e: String) -> Unit): DataResult<R>{
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }

    if (this is DataResult.Failure){
        action.invoke(msg)
    }
    return this
}

/**
 * 网络请求成功但接口返回错误
 */
@OptIn(ExperimentalContracts::class)
inline fun <R>DataResult<R>.onError(action: (code: Int,msg: String) -> Unit): DataResult<R>{
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }

    if (this is DataResult.Error){
        action.invoke(code,msg)
    }
    return this
}

/**
 * 接口返回空
 */
@OptIn(ExperimentalContracts::class)
inline fun <R>DataResult<R>.onEmpty(action: () -> Unit): DataResult<R>{
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }

    if (this is DataResult.Empty){
        action.invoke()
    }
    return this
}