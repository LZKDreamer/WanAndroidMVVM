package com.lzk.wanandroidmvvm

import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.lzk.libcommon.base.BaseViewModel
import com.lzk.libcommon.network.*
import com.lzk.libcommon.network.net.requestAsync
import com.lzk.libcommon.network.net.requestAwait
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
            .requestAwait()
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

    fun getAsync() = launch {
        RxHttp.get("https://www.wanandroid.com/article/top/json")
            .toResponse<List<ArticleData>>()
            .requestAsync(this)
            .onSuccess {
                Log.d("TAG","async 1")
                liveSuccess.postValue("async 1成功啦! ${Looper.getMainLooper() == Looper.myLooper()}")
            }
        RxHttp.get("https://www.wanandroid.com/article/top/json")
            .toResponse<List<ArticleData>>()
            .requestAsync(this)
            .onSuccess {
                Log.d("TAG","async 2")
                liveSuccess.postValue("async 2成功啦!")
            }
    }
}