package com.lzk.libcommon.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * @Author: LiaoZhongKai
 * @Date: 2021/6/7 16:22
 * @Description:
 */
abstract class BaseFragment<T: ViewDataBinding> : Fragment() {

    protected lateinit var mBinding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater,getLayoutRes(),container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getExtras(arguments)
        initView(view, savedInstanceState)
        initData()
        initEvent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (this::mBinding.isInitialized){
            mBinding.unbind()
        }
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    fun getExtras(args: Bundle?){}

    abstract fun initView(view: View,savedInstanceState: Bundle?)

    abstract fun initData()

    abstract fun initEvent()

    protected inline fun <T : Any?> LiveData<T>.observeKt(crossinline block: (T?) -> Unit) {
        this.observe(viewLifecycleOwner, Observer {
            block(it)
        })
    }
}