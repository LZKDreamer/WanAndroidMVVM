package com.lzk.libcommon.base

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * @Author: LiaoZhongKai
 * @Date: 2021/6/7 16:07
 * @Description:
 */
abstract class BaseActivity<T: ViewDataBinding> : AppCompatActivity(){

    protected lateinit var mBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,getLayoutRes())
        mBinding.lifecycleOwner = this
        getExtras(intent)
        initView(savedInstanceState)
        observe()
        initData()
        initEvent()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::mBinding.isInitialized){
            mBinding.unbind()
        }
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    open fun getExtras(intent: Intent?){}

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun initData()

    abstract fun initEvent()

    open fun observe(){

    }

    protected fun showToast(text: String?){
        text?.let {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        }
    }

    protected inline fun <T : Any?> LiveData<T>.observeKt(crossinline block: (T?) -> Unit) {
        this.observe(this@BaseActivity, Observer {
            block(it)
        })
    }
}