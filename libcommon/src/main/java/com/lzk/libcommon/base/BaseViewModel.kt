package com.lzk.libcommon.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @Author: LiaoZhongKai
 * @Date: 2021/6/7 16:59
 * @Description:
 */
class BaseViewModel : ViewModel() {

    private val jobs = mutableListOf<Job>()

    protected fun launch(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        kotlin.runCatching {
            block.invoke(this)
        }.onFailure {

        }
    }.addTo(jobs)

    override fun onCleared() {
        jobs.forEach { it.cancel() }
        super.onCleared()
    }

    private fun Job.addTo(list: MutableList<Job>) {
        list.add(this)
    }
}