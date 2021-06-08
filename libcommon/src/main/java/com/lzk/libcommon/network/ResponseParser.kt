package com.lzk.libcommon.network

import okhttp3.Response
import okio.IOException
import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.entity.ParameterizedTypeImpl
import rxhttp.wrapper.parse.AbstractParser
import rxhttp.wrapper.utils.convert
import java.lang.reflect.Type

/**
 * @Author: LiaoZhongKai
 * @Date: 2021/6/8 11:00
 * @Description:
 */
@Parser(name = "Response")
open class ResponseParser<T> : AbstractParser<T>{

    protected constructor() : super()

    @Throws(IOException::class)
    override fun onParse(response: Response): T {
        val type: Type = ParameterizedTypeImpl[BaseResp::class.java, mType] //获取泛型类型
        val data: BaseResp<T> = response.convert(type)   //获取Response对象
        val t = data.data                             //获取data字段
        if (data.isSuccess()){//成功
            if (data.data == null || (data.data is List<*> && (data.data as List<*>).isEmpty())){
                throw DataNullException()
            }
        }else{//失败
            throw ApiException(data.errorCode,data.errorMsg)
        }
        return t!!
    }

}