package com.lzk.wanandroidmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.lzk.libcommon.base.BaseActivity
import com.lzk.wanandroidmvvm.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mViewModel by viewModels<VM> { defaultViewModelProviderFactory }

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {

    }

    override fun initEvent() {
       mViewModel.getHot()
    }

    override fun observe() {
        super.observe()
        mViewModel.apply {
            liveSuccess.observeKt {
                showToast(it)
            }

            liveError.observeKt {
                showToast(it)
            }
        }
    }
}