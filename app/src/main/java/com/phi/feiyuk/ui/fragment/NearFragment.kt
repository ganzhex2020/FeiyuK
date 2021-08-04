package com.phi.feiyuk.ui.fragment

import com.phi.basemodule.base.BaseFragment
import com.phi.feiyuk.R
import com.phi.feiyuk.utils.DeviceUtils
import kotlinx.android.synthetic.main.fragment_near.*


class NearFragment:BaseFragment() {

    override fun getLayoutResId(): Int = R.layout.fragment_near

    override fun initView() {
        ll_near.setPadding(0, DeviceUtils.getStatusBarHeight(requireContext()), 0, 0)
    }

    override fun initData() {

    }
}