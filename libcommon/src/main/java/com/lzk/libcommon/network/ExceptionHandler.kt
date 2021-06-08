package com.lzk.libcommon.network

import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import org.json.JSONException
import rxhttp.wrapper.exception.HttpStatusCodeException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * @Author LiaoZhongKai
 * @Date 2021/3/2 18:33
 * @Description 异常信息处理类
 */
object ExceptionHandler {
    fun getErrorMessage(t: Throwable): String{
        val msg = t.message
        return when(t){
            is ConnectException -> "网络错误,请检查网络后重试"
            is UnknownHostException ->  "网络不可用，请检查网络后重试"
            is IllegalArgumentException -> "非法数据异常"
            is SocketTimeoutException -> "请求网络超时，请检查网络后重试"
            is HttpStatusCodeException -> "请求失败(${t.statusCode})"
            is JsonParseException, is ParseException,is JSONException, is JsonIOException -> "数据解析错误"
            else -> msg?:"请求失败，请稍后再试"
        }
    }
}