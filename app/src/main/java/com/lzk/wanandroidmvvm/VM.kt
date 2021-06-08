package com.lzk.wanandroidmvvm

import androidx.lifecycle.MutableLiveData
import com.lzk.libcommon.base.BaseViewModel
import com.lzk.libcommon.network.*
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

/**
 * @Author: LiaoZhongKai
 * @Date: 2021/6/8 15:34
 * @Description:
 */
class VM : BaseViewModel() {
    val liveSuccess = MutableLiveData<String>()
    val liveError = MutableLiveData<String>()

    fun getHot() = launch {
        RxHttp.get("https://www.wanandroid.com/article/top/json")
            //            .toClass<BaseResp<List<ArticleData>>>()
            .toResponse<List<ArticleData>>()
            .request()
            .onSuccess {
                liveSuccess.postValue("成功啦!")
            }
            .onFailure {
                liveError.postValue("onFailure:${it}")
            }
            .onError { code, msg ->
                liveError.postValue("onError:${msg}")
            }
            .onEmpty {
                liveError.postValue("onEmpty")
            }
    }
}