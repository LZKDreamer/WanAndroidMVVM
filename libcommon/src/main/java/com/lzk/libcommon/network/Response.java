package com.lzk.libcommon.network;

import androidx.annotation.Keep;

/**
 * @Author: LiaoZhongKai
 * @Date: 2021/6/8 16:58
 * @Description:
 */
@Keep
public class Response<T> {
    int errorCode;
    String errorMsg;
    T data;

    Boolean isSuccess(){
        return errorCode == 0;
    }
}
