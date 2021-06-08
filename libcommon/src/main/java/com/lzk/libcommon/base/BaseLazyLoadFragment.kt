package com.lzk.libcommon.base

import androidx.databinding.ViewDataBinding

/**
 * @Author: LiaoZhongKai
 * @Date: 2021/6/7 16:33
 * @Description: 懒加载Fragment
 */
abstract class BaseLazyLoadFragment<T: ViewDataBinding> : BaseFragment<T>(){

    private var mIsLoaded = false

    override fun onResume() {
        super.onResume()
        if (!mIsLoaded && !isHidden){
            onLazyLoad()
            mIsLoaded = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mIsLoaded = false
    }

    /**
     * 在页面第一次可见时加载数据
     */
    abstract fun onLazyLoad()
}